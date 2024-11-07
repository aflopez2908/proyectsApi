package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.model.HistorialTarea;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService tareasService;

/*    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Tarea> getAllTareas() {
        return tareasService.getAllTareas();
    }*/
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createTarea(@RequestBody Tarea tarea) {
        System.out.println("Task controller: " + tarea.toString());
        try {
            Tarea tareaCreada = tareasService.createTarea(tarea);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tarea creada exitosamente");
            response.put("tareaId", tareaCreada.getTareaId().toString());
            HistorialTarea historialtarea = tareasService.createhistorialTarea(tareaCreada);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Tarea>> getAllTask() {
        try {
            List<Tarea> tarea = tareasService.getAllTareas();
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}



