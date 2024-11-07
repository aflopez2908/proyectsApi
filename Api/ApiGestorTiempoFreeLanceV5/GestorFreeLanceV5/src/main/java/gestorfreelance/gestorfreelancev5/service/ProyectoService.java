package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.CorreoUsuarioNoDisponibleException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.exception.ResourceNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
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




    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }

    public Proyecto obtenerProyectoPorId(Long proyecto_id) {
        return proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyecto_id));
    }


    public Proyecto crearProyecto(ProyectoDTO proyecto) {
        Proyecto proyectoExistente= proyectoRepository.findByNombre(proyecto.getProyecto().getNombre());
        if (proyectoExistente != null) {
            throw new IllegalArgumentException("El proyecto ya existe y no se puede crear.");
        }
        //falta insertar el numero de horas
        EstadoProyecto estadoProyecto = estadoProyectoRepository.findById(1);
        Proyecto nuevoProyecto = proyectoRepository.save(proyecto.getProyecto());
        Cliente cliente = proyectoRepository.findClienteByProyectoId(proyecto.getProyecto().getProyectoId().longValue());

        Usuario miusuario= usuariosRepository.findMatchClient(proyecto.getProyecto().getProyectoId().longValue());
        System.out.println(miusuario);

        // Crear y guardar la BolsaHora
        BolsaHora bolsaHora = new BolsaHora();
        bolsaHora.setUsuario(miusuario);
        bolsaHora.setProyecto(nuevoProyecto);
        bolsaHora.setHorasTotales(proyecto.getHorasAsignadas());
        bolsaHora.setHorasUsadas(0);
        bolsaHora.setHorasRestantes(proyecto.getHorasAsignadas());
        bolsaHorasRepository.save(bolsaHora);


        //envio de mensaje de creacion , debe ser el mismo email del cliente
        String subject = "Creacion de un nuevo proyecto";
        String body = "Hola " + cliente.getNombre() + ",\n\n" +
                "Te informamos la creacion de un nuevo proyecto:\n" +
                "Nombre de proyecto: " + proyecto.getProyecto().getNombre() + "\n" +
                "descripcion: " + proyecto.getProyecto().getDescripcion() + "\n\n" +
                "Si tienes alguna pregunta o problema, no dudes en contactarnos.\n\n" +
                "Saludos,\n" +
                "El equipo de soporte";

        enviarCorreoBienvenida(cliente,subject,body);
        registrarEstado(proyecto.getProyecto(), estadoProyecto, "Creacion del proyecto",1);
        return nuevoProyecto;
    }

    public Proyecto cambioEstado(int id,int estado) {
        Proyecto proyecto= proyectoRepository.findByProyectoId(id);
        EstadoProyecto estadoProyecto = estadoProyectoRepository.findById(estado);
        boolean consulta =proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(proyecto.getProyectoId().longValue());
        if(consulta == true) {
            throw new ProyectoTerminadoException("El proyecto ya fue terminado y no puede cambiar de estado");
        }
        proyectoEstadoRepository.actualizarVigencia((long) id);
        registrarEstado(proyecto, estadoProyecto, "actualizacion",1);
        return proyecto;

    }

    public Proyecto actualizarProyecto(Long proyecto_id, Proyecto detallesProyecto) {
        Proyecto proyecto = proyectoRepository.findById(proyecto_id).orElseThrow();
        Proyecto proyectoExistente= proyectoRepository.findByNombre(proyecto.getNombre());
        if (proyectoExistente != null) {
            throw new IllegalArgumentException("El nombre ya esta asignado a otro proyecto");
        }

        proyectoExistente = proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con el ID: " + proyecto_id));
        proyectoExistente.setNombre(detallesProyecto.getNombre());
        proyectoExistente.setDescripcion(detallesProyecto.getDescripcion());
        proyectoExistente.setCliente(detallesProyecto.getCliente());
        proyectoExistente.setFechaInicio(detallesProyecto.getFechaInicio());
        proyectoExistente.setFechaFin(detallesProyecto.getFechaFin());
        Proyecto proyectoActualizado = proyectoRepository.save(proyectoExistente);
        return proyectoActualizado;
    }

    public void eliminarProyecto(Long proyecto_id) {
        Proyecto proyecto = proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyecto_id));
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

