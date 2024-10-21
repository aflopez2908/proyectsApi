package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.ProyectoNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.HistorialTareasRepository;
import gestorfreelance.gestorfreelancev5.repository.ProyectosRepository;
import gestorfreelance.gestorfreelancev5.repository.TareasRepository;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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

    public List<Tarea> getAllTareas() {
        return tareasRepository.findAll();
    }

    public Tarea createTarea(Tarea tarea) {
        Proyecto proyecto = tarea.getProyecto();
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
        Usuario usuario = usuariosRepository.findByUsuarioId(1);
        historialTarea.setUsuario(usuario);
        historialTarea.setVigente(1);
        EstadoTarea estadoTarea = new EstadoTarea();
        estadoTarea.setEstadoId(1);
        historialTarea.setEstadoTarea(estadoTarea);
       return historialTareasRepository.save(historialTarea);
    }

/*    public Tarea updateTarea(Integer tareaId, Tarea tareaActualizada) {
        Optional<Tarea> tareaExistente = tareasRepository.findById(tareaId);

        if (tareaExistente.isPresent()) {
            Tarea tarea = tareaExistente.get();
            tarea.setNombre(tareaActualizada.getNombre());
            tarea.setDescripcion(tareaActualizada.getDescripcion());
            tarea.setFechaInicio(tareaActualizada.getFechaInicio());
            tarea.setFechaFin(tareaActualizada.getFechaFin());
            tarea.setAsignadoA(tareaActualizada.getAsignadoA());
            return tareasRepository.save(tarea);
        } else {
            throw new RuntimeException("Tarea con ID " + tareaId + " no encontrada.");
        }
    }*/

}
