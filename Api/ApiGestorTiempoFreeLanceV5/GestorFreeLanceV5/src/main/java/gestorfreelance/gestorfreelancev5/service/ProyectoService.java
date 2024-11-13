package gestorfreelance.gestorfreelancev5.service;
import java.lang.reflect.Field;

import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.*;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProyectoService {


    @Autowired
    private ProyectosRepository proyectoRepository;

    @Autowired
    private EstadosProyectoRepository estadoProyectoRepository;

    @Autowired
    private ProyectoEstadoRepository proyectoEstadoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private BolsaHorasRepository bolsaHorasRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientesRepository clientesRepository;




    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }

    public Proyecto obtenerProyectoPorId(Long proyecto_id) {
        return proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyecto_id));
    }

    @Transactional
    public Proyecto crearProyecto(ProyectoDTO proyecto) {

        if (proyecto.getFechaInicio().isAfter(proyecto.getFechaFin())) {
            throw new InvalidDataException("La fecha de fin no puede ser menor a la de inicio");
        }


        proyecto.setProyectoId(0);

        validateFieldsNotNull(proyecto);

        // Crear nuevo proyecto
        Proyecto proyectoN = new Proyecto();
        proyectoN.setNombre(proyecto.getNombre());
        proyectoN.setDescripcion(proyecto.getDescripcion());
        proyectoN.setCliente(clientesRepository.findById(proyecto.getClienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado")));

        proyectoN.setFechaInicio(proyecto.getFechaInicio());
        proyectoN.setFechaFin(proyecto.getFechaFin());

        // Verificar si el proyecto ya existe
        Proyecto proyectoExistente = proyectoRepository.findByNombre(proyecto.getNombre());
        if (proyectoExistente != null) {
            throw new ProyectoExistenteException("El proyecto ya existe y no se puede crear.");
        }
        Proyecto nuevoProyecto = proyectoRepository.save(proyectoN);
        Cliente cliente = proyectoRepository.findClienteByProyectoId(nuevoProyecto.getProyectoId().longValue());


        // Crear y guardar la BolsaHora
        BolsaHora bolsaHora = new BolsaHora();
        bolsaHora.setProyecto(nuevoProyecto);
        bolsaHora.setHorasTotales(proyecto.getHorasAsignadas());
        bolsaHora.setHorasUsadas(0);
        bolsaHora.setHorasRestantes(proyecto.getHorasAsignadas());
        bolsaHorasRepository.save(bolsaHora);

        // Enviar correo de bienvenida
        String subject = "Creacion de un nuevo proyecto";
        String body = "Hola " + cliente.getNombre() + ",\n\n" +
                "Te informamos la creacion de un nuevo proyecto:\n" +
                "Nombre de proyecto: " + proyecto.getNombre() + "\n" +
                "descripcion: " + proyecto.getDescripcion() + "\n\n" +
                "Si tienes alguna pregunta o problema, no dudes en contactarnos.\n\n" +
                "Saludos,\n" +
                "El equipo de soporte";
        enviarCorreoBienvenida(cliente, subject, body);

        // Registrar estado del proyecto
        EstadoProyecto estadoProyecto = estadoProyectoRepository.findById(1);
        registrarEstado(nuevoProyecto, estadoProyecto, "Creacion del proyecto", 1);

        return nuevoProyecto;
    }

    public void validateFieldsNotNull(ProyectoDTO proyectoDTO) {
        for (Field field : proyectoDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);  // Permite acceder a los campos privados

            try {
                Object value = field.get(proyectoDTO);
                if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                    throw new InvalidDataException("El campo '" + field.getName() + "' no puede estar vacío o nulo.");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al acceder a los campos de ProyectoDTO");
            }
        }
    }


    public ProyectoEstado cambioEstado(int id,int estado) {
        Proyecto proyecto= proyectoRepository.findByProyectoId(id);
        EstadoProyecto estadoProyecto = estadoProyectoRepository.findById(estado);
        if (proyecto == null) {
            throw new ResourceNotFoundException("Proyecto no encontrado con id: " + id);
        }
        if (estadoProyectoRepository.findById(estado) == null) {
            throw new IllegalArgumentException("El estado proporcionado no es válido. Por favor, elija un estado correcto.");
        }

        boolean consulta =proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(proyecto.getProyectoId().longValue());
        if(consulta == true) {
            throw new ProyectoTerminadoException("El proyecto ya fue terminado y no puede cambiar de estado");
        }

        if (estadoProyecto.getEstadoId() == 2 || estadoProyecto.getEstadoId() == 3 ) {
            if (estado==1){
                throw new IllegalArgumentException("El estado proporcionado es de cracion y el proyecto ya fue creado");

            }
        }
        proyectoEstadoRepository.actualizarVigencia((long) id);
        registrarEstado(proyecto, estadoProyecto, "actualizacion",1);
        ProyectoEstado proyectos = proyectoEstadoRepository.findByProyectoId((long) id);
        return proyectos;

    }

    @Transactional
    public Proyecto actualizarProyecto(Long proyecto_id, Proyecto detallesProyecto) {
        // Verificar que el proyecto exista
        Proyecto proyectoExistente = proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ProyectoNoEncontradoException(proyecto_id));

        boolean proyectoTerminado = proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(proyectoExistente.getProyectoId().longValue());
        if (proyectoTerminado) {
            throw new ProyectoTerminadoException("El proyecto ya esta terminado no se puede cambiar el estado");
        }

        // Validar que el nombre y otros campos no sean nulos o vacíos
        validarProyecto(detallesProyecto);


        //verificar si el nombre ya esta en uso

        if (proyectoExistente != null) {
            throw new IllegalArgumentException("El proyecto ya existe y no se puede crear.");
        }

        // Verificar si el nombre del proyecto está siendo cambiado
        if (!detallesProyecto.getNombre().equals(proyectoExistente.getNombre())) {
            validarNombreUnico(detallesProyecto.getNombre());
        }

        // Actualizar los detalles del proyecto
        proyectoExistente.setNombre(detallesProyecto.getNombre());
        proyectoExistente.setDescripcion(detallesProyecto.getDescripcion());
        proyectoExistente.setCliente(detallesProyecto.getCliente());
        proyectoExistente.setFechaInicio(detallesProyecto.getFechaInicio());
        proyectoExistente.setFechaFin(detallesProyecto.getFechaFin());

        // Guardar el proyecto actualizado
        return proyectoRepository.save(proyectoExistente);
    }

    private void validarProyecto(Proyecto proyecto) {
        if (proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proyecto no puede ser nulo o vacío.");
        }

        if (proyecto.getDescripcion() == null || proyecto.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción del proyecto no puede ser nula o vacía.");
        }

        if (proyecto.getCliente() == null) {
            throw new IllegalArgumentException("El cliente del proyecto no puede ser nulo.");
        }

        if (proyecto.getFechaInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio del proyecto no puede ser nula.");
        }

        if (proyecto.getFechaFin() == null) {
            throw new IllegalArgumentException("La fecha de fin del proyecto no puede ser nula.");
        }

        if (proyecto.getFechaInicio().isAfter(proyecto.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
    }
    private void validarNombreUnico(String nombre) {
        Proyecto proyectoConMismoNombre = proyectoRepository.findByNombre(nombre);
        if (proyectoConMismoNombre != null) {
            throw new IllegalArgumentException("Ya existe un proyecto con el nombre '" + nombre + "'.");
        }}

    @Transactional
    public void eliminarProyecto(Long proyecto_id) {
        Proyecto proyecto = proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ProyectoNoEncontradoException(proyecto_id));
        proyectoRepository.eliminarRelacionesPorProyectoId(proyecto_id);
        proyectoRepository.delete(proyecto);
    }

    private void registrarEstado(Proyecto proyecto, EstadoProyecto estado, String comentario, int vigencia) {
        ProyectoEstado proyectoEstado = new ProyectoEstado();
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setEstado(estado);
        proyectoEstado.setFechaCambio(new java.sql.Date(System.currentTimeMillis()));
        proyectoEstado.setComentario(comentario);
        proyectoEstado.setVigencia(vigencia);
        proyectoEstadoRepository.save(proyectoEstado);
    }

    private void enviarCorreoBienvenida(Cliente cliente, String subject, String body) throws CorreoUsuarioNoDisponibleException {
        String to = cliente.getEmail();
        if (to == null || to.isEmpty()) {
            throw new CorreoUsuarioNoDisponibleException("El correo del usuario no está disponible para enviar la notificación.");
        }

        emailService.sendEmailwithAttachment(to, subject, body);
    }


}

