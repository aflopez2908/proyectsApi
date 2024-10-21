package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService tareasService;

    @GetMapping
    public List<Tarea> getAllTareas() {
        return tareasService.getAllTareas();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createTarea(@RequestBody Tarea tarea) {
        System.out.println("Task controller: " + tarea.toString());
        try {
            Tarea tareaCreada = tareasService.createTarea(tarea);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tarea creada exitosamente");
            response.put("tareaId", tareaCreada.getTareaId().toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

/*    public Tarea createTarea(@RequestBody Tarea tarea)
    {
        System.out.println("Task controller:" + tarea.toString());
        try {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tarea creada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return tareasService.createTarea(tarea);
    }*/

/*    @PutMapping("/{id}")
    public Tarea updateTarea(@PathVariable Integer id, @RequestBody Tarea tarea) {
        return tareasService.updateTarea(id, tarea);
    }*/

