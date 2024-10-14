package gestorfreelance.gestorfreelancev5.services;

import gestorfreelance.gestorfreelancev5.model.Tareas;
import gestorfreelance.gestorfreelancev5.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TareasRepository tareasRepository;


    public List<Tareas> getAllTareas() {
        return tareasRepository.findAll();
    }

    public Tareas createTarea(Tareas tarea) {
        return tareasRepository.save(tarea);
    }
    public Tareas updateTarea(Integer tareaId, Tareas tareaActualizada) {
        Optional<Tareas> tareaExistente = tareasRepository.findById(tareaId);

        if (tareaExistente.isPresent()) {
            Tareas tarea = tareaExistente.get();
            tarea.setNombre(tareaActualizada.getNombre());
            tarea.setDescripcion(tareaActualizada.getDescripcion());
            tarea.setFechaInicio(tareaActualizada.getFechaInicio());
            tarea.setFechaFin(tareaActualizada.getFechaFin());
            tarea.setEstado(tareaActualizada.getEstado());
            tarea.setProyecto(tareaActualizada.getProyecto());
            tarea.setAsignadoA(tareaActualizada.getAsignadoA());
            return tareasRepository.save(tarea);
        } else {
            throw new RuntimeException("Tarea con ID " + tareaId + " no encontrada.");
        }
    }

}
