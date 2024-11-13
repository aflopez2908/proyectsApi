package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.ProyectoNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TareasRepository tareasRepository;
    @Autowired
    private HistorialTareasRepository historialTareasRepository;
    @Autowired
    private ProyectosRepository proyectosRepository;
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private PrioridadesTareaRepository prioridadesTareaRepository;
    @Autowired
    private TareasPrioridadesRepository tareasPrioridadesRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;


    public List<Tarea> getAllTareas() {
        return tareasRepository.findAll();
    }
    @Transactional
    public Tarea createTarea(Tarea tarea) {
        System.out.println("Tarea: " + tarea);
        Proyecto proyecto = tarea.getProyecto();
        System.out.println("Proyecto:" + proyecto);
        Proyecto proyectoExistente = proyectosRepository.findByProyectoId(proyecto.getProyectoId());
        if (proyectoExistente == null) {
            throw new ProyectoNotFoundException("El proyecto con ID " + proyecto.getProyectoId() + " no existe");
        }
        return tareasRepository.save(tarea);
    }

    public HistorialTarea createhistorialTarea(Tarea tarea) {
        HistorialTarea historialTarea = new HistorialTarea();
        historialTarea.setTarea(tarea);
        historialTarea.setCambio("New Task");
        historialTarea.setFechaCambio(LocalDateTime.now());
        String userCordinador = "Coordinador";
        Usuario  usuario = usuariosRepository.findByNombre(userCordinador)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe."));;
        historialTarea.setUsuario(usuario);
        historialTarea.setVigente(1);
        EstadoTarea estadoTarea = new EstadoTarea();
        estadoTarea.setEstadoId(1);
        historialTarea.setEstadoTarea(estadoTarea);
        historialTarea.setPrioridadTarea(prioridadesTareaRepository.findByPrioridadId(1));
        Proyecto proyecto = proyectosRepository.findByProyectoId(tarea.getProyecto().getProyectoId());
        String emailHead  = "Nueva tarea creada: "
                + tarea.getNombre() + " en el proyecto"
                + proyecto.getNombre();

        String emailBody = "Solicitud tarea : "
                + tarea.getDescripcion() + " en el proyecto"
                + proyecto.getNombre();
        System.out.println("Body: " + emailBody);

        emailService.sendEmailwithAttachment(usuario.getEmail(), emailHead, emailBody);
        return historialTareasRepository.save(historialTarea);
    }
    @Transactional
    public HistorialTarea updateTarea(Long tareaId, String descripcionCambio, int nuevaPrioridad, int nuevoEstado, Long usuarioId) {

        // Buscar la tarea por ID
        Optional<Tarea> tareaExistenteOpt = tareasRepository.findById(tareaId.intValue());
        if (tareaExistenteOpt.isEmpty()) {
            throw new ProyectoNotFoundException("La tarea con ID " + tareaId + " no existe.");
        }
        Tarea tareaExistente = tareaExistenteOpt.get();

        // Verificar que el estado no sea 0 ni 4
        HistorialTarea historialTareaestado = historialTareasRepository.findByTareaAndVigente(tareaExistente,1);

        if (historialTareaestado != null) {
            if (historialTareaestado.getEstadoTarea().getEstadoId() == 4 ) {
                throw new IllegalArgumentException("La Tarea no se puede modificar ya se encuentra Finalizada");
            }
        }
        //if (nuevoEstado == 0 || nuevoEstado == 4) {
        if (nuevoEstado == 0 ) {
                throw new IllegalArgumentException("La Tarea ya se encuentra creada, estado 0 no permitido.");
        }

        // Marcar los registros anteriores como no vigentes (`vigente = 0`)
        List<HistorialTarea> historialTareas = historialTareasRepository.findByTarea(tareaExistente);
        for (HistorialTarea historial : historialTareas) {
            if (historial.getVigente() == 1) { // Solo marcar los que estén vigentes
                historial.setVigente(0);
                historialTareasRepository.save(historial);
            }
        }

        // Buscar el usuario que realiza el cambio
        Usuario usuario = usuariosRepository.findById(usuarioId.intValue())
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con ID " + usuarioId + " no existe."));


        // Crear un nuevo registro de historial con los cambios
        HistorialTarea nuevoHistorial = new HistorialTarea();
        nuevoHistorial.setTarea(tareaExistente);
        nuevoHistorial.setCambio(descripcionCambio);
        nuevoHistorial.setFechaCambio(LocalDateTime.now());
        nuevoHistorial.setVigente(1); // El nuevo registro es el vigente
        nuevoHistorial.setUsuario(usuario); // Asignar el usuario

        // Establecer el nuevo estado y prioridad
        EstadoTarea nuevoEstadoTarea = new EstadoTarea();
        nuevoEstadoTarea.setEstadoId(nuevoEstado);
        nuevoHistorial.setEstadoTarea(nuevoEstadoTarea);
        nuevoHistorial.setPrioridadTarea(prioridadesTareaRepository.findByPrioridadId(nuevaPrioridad));



        PrioridadTarea prioridadTarea = new PrioridadTarea();
        prioridadTarea = prioridadesTareaRepository.findByPrioridadId(nuevaPrioridad);

       //Enviar actualizacion de correo
        String emailHead  = "Actualización Tarea: "
                + nuevoHistorial.getTarea().getTareaId() + " en el proyecto";

        String emailBody = "Actualizacion - tarea : "
                + nuevoHistorial.getCambio()
                + "Prioridad: "  + prioridadTarea.getNombre() + "Validar";

        System.out.println("Head: " + emailHead);
        System.out.println("Body: " + emailBody);

        emailService.sendEmailwithAttachment(usuario.getEmail(), emailHead, emailBody);
        // Guardar el nuevo registro en la base de datos
        return historialTareasRepository.save(nuevoHistorial);
    }

}
