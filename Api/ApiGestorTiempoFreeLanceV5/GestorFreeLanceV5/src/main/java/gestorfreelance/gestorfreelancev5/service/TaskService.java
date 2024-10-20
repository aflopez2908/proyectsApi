package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TareasRepository tareasRepository;


    public List<Tarea> getAllTareas() {
        return tareasRepository.findAll();
    }

    public Tarea createTarea(Tarea tarea) {

        return tareasRepository.save(tarea);
    }
    public Tarea updateTarea(Integer tareaId, Tarea tareaActualizada) {
        Optional<Tarea> tareaExistente = tareasRepository.findById(tareaId);

        if (tareaExistente.isPresent()) {
            Tarea tarea = tareaExistente.get();
            tarea.setNombre(tareaActualizada.getNombre());
            tarea.setDescripcion(tareaActualizada.getDescripcion());
            tarea.setFechaInicio(tareaActualizada.getFechaInicio());
            tarea.setFechaFin(tareaActualizada.getFechaFin());
            tarea.setProyecto(tareaActualizada.getProyecto());
            tarea.setAsignadoA(tareaActualizada.getAsignadoA());
            return tareasRepository.save(tarea);
        } else {
            throw new RuntimeException("Tarea con ID " + tareaId + " no encontrada.");
        }
    }

}
