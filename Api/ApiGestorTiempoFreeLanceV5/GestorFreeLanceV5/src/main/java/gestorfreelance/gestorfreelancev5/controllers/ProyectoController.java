package gestorfreelance.gestorfreelancev5.controllers;



import java.util.List;

import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author pipel
 */
@RestController
@RequestMapping({"api/proyectosss"})

public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    //lectura de todos los proyectos funcionando
    @GetMapping({"/read"})
    public ResponseEntity<List<Proyectos>> getAllProyectos() {
        List<Proyectos> proyectos = proyectoService.getAllProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    // Crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<Proyectos> crearProyecto(@RequestBody Proyectos proyecto) {
        Proyectos nuevoProyecto = proyectoService.crearProyecto(proyecto);
        return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    }




    // Obtener un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proyectos> obtenerProyectoPorId(@PathVariable Long id) {
        Proyectos proyecto = proyectoService.obtenerProyectoPorId(id);
        return ResponseEntity.ok(proyecto);
    }

    // Actualizar un proyecto
    @PutMapping("/{id}")
    public ResponseEntity<Proyectos> actualizarProyecto(@PathVariable Long id, @RequestBody Proyectos proyectoDetalles) {
        Proyectos proyectoActualizado = proyectoService.actualizarProyecto(id, proyectoDetalles);
        return ResponseEntity.ok(proyectoActualizado);
    }

    // Eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.noContent().build();
    }
}


