package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.ProyectoNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EmailService emailService;
    @Autowired
    private BolsaHorasRepository bolsaHoraRepository;

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

        BolsaHora bolsaHora = bolsaHoraRepository.findByProyecto_ProyectoId(proyecto.getProyectoId())
                .orElseThrow(() -> new RuntimeException("El proyecto con ID " + proyecto.getProyectoId() + " no tiene una bolsa de horas asignada."));

        if (bolsaHora.getHorasRestantes() <= 0) {
            throw new RuntimeException("El proyecto con ID " + proyecto.getProyectoId() + " no cuenta con horas suficientes para la creación de tareas.");
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
    public HistorialTarea updateTarea(Long tareaId, String descripcionCambio, int nuevaPrioridad, int nuevoEstado, Long usuarioId, Integer  horasConsumidas) {

        Optional<Tarea> tareaExistenteOpt = tareasRepository.findById(tareaId.intValue());
        if (tareaExistenteOpt.isEmpty()) {
            throw new ProyectoNotFoundException("La tarea con ID " + tareaId + " no existe.");
        }
        Tarea tareaExistente = tareaExistenteOpt.get();

        HistorialTarea historialTareaestado = historialTareasRepository.findByTareaAndVigente(tareaExistente,1);
        if (historialTareaestado != null) {
            if (historialTareaestado.getEstadoTarea().getEstadoId() == 4 ) {
                throw new IllegalArgumentException("La Tarea no se puede modificar ya se encuentra Finalizada");
            }
        }
        if (nuevoEstado == 0 ) {
                throw new IllegalArgumentException("La Tarea ya se encuentra creada, estado 0 no permitido.");
        }
        if (nuevoEstado == 4 ) {
            if (horasConsumidas == null) {
                throw new IllegalArgumentException("Para finalizar la tarea debe inputar las horas de cierre");
            }
        }

        List<HistorialTarea> historialTareas = historialTareasRepository.findByTarea(tareaExistente);
        for (HistorialTarea historial : historialTareas) {
            if (historial.getVigente() == 1) { // Solo marcar los que estén vigentes
                historial.setVigente(0);
                historialTareasRepository.save(historial);
            }
        }

        Usuario usuario = usuariosRepository.findById(usuarioId.intValue())
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con ID " + usuarioId + " no existe."));


        if (horasConsumidas != null) {
            if (horasConsumidas <= 0) {
                throw new IllegalArgumentException("Las horas consumidas deben ser un número positivo.");
            }

            BolsaHora bolsaHora = bolsaHoraRepository.findByProyecto_ProyectoId(tareaExistente.getProyecto().getProyectoId())
                    .orElseThrow(() -> new RuntimeException("El proyecto con ID " + tareaExistente.getProyecto().getProyectoId() + " no tiene una bolsa de horas asignada."));

            if (bolsaHora.getHorasRestantes() < horasConsumidas) {
                throw new RuntimeException("El proyecto con ID " + tareaExistente.getProyecto().getProyectoId() + " no tiene suficientes horas restantes.");
            }

            bolsaHora.setHorasUsadas(bolsaHora.getHorasUsadas() + horasConsumidas);
            bolsaHora.setHorasRestantes(bolsaHora.getHorasRestantes() - horasConsumidas);
            bolsaHoraRepository.save(bolsaHora);
        }

        HistorialTarea nuevoHistorial = new HistorialTarea();
        nuevoHistorial.setTarea(tareaExistente);
        nuevoHistorial.setCambio(descripcionCambio);
        nuevoHistorial.setFechaCambio(LocalDateTime.now());
        nuevoHistorial.setVigente(1);
        nuevoHistorial.setUsuario(usuario);

        EstadoTarea nuevoEstadoTarea = new EstadoTarea();
        nuevoEstadoTarea.setEstadoId(nuevoEstado);
        nuevoHistorial.setEstadoTarea(nuevoEstadoTarea);
        nuevoHistorial.setPrioridadTarea(prioridadesTareaRepository.findByPrioridadId(nuevaPrioridad));

        PrioridadTarea prioridadTarea = new PrioridadTarea();
        prioridadTarea = prioridadesTareaRepository.findByPrioridadId(nuevaPrioridad);

        String emailHead  = "Actualización Tarea: "
                + nuevoHistorial.getTarea().getTareaId() + " en el proyecto";

        String emailBody = "Actualizacion - tarea : "
                + nuevoHistorial.getCambio()
                + "Prioridad: "  + prioridadTarea.getNombre() + "Validar";

        System.out.println("Head: " + emailHead);
        System.out.println("Body: " + emailBody);

        emailService.sendEmailwithAttachment(usuario.getEmail(), emailHead, emailBody);
        return historialTareasRepository.save(nuevoHistorial);
    }
}
