package gestorfreelance.gestorfreelancev5.controllers;

import gestorfreelance.gestorfreelancev5.services.TaskService;
import gestorfreelance.gestorfreelancev5.model.Tareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {
    @Autowired
    private  TaskService tareasService;

    @GetMapping
    public List<Tareas> getAllTareas() {
        return tareasService.getAllTareas();
    }

    @PostMapping
    public Tareas createTarea(@RequestBody Tareas tarea) {
        return tareasService.createTarea(tarea);
    }

    @PutMapping("/{id}")
    public Tareas updateTarea(@PathVariable Integer id, @RequestBody Tareas tarea) {
        return tareasService.updateTarea(id, tarea);
    }
}
