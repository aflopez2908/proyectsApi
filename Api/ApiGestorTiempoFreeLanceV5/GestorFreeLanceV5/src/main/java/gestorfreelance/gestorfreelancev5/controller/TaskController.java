package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {
    @Autowired
    private  TaskService tareasService;

    @GetMapping
    public List<Tarea> getAllTareas() {
        return tareasService.getAllTareas();
    }

    @PostMapping
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return tareasService.createTarea(tarea);
    }

    @PutMapping("/{id}")
    public Tarea updateTarea(@PathVariable Integer id, @RequestBody Tarea tarea) {
        return tareasService.updateTarea(id, tarea);
    }
}
