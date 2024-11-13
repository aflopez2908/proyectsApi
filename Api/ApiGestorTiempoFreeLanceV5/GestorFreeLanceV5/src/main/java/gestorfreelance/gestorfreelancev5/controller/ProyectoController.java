package gestorfreelance.gestorfreelancev5.controller;
import java.util.List;

import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.ProyectoNoEncontradoException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.model.EstadoProyecto;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import gestorfreelance.gestorfreelancev5.service.ProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping({"/api/v1/proyecto"})

public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;


    @Operation(summary = "Obtiene todos los proyectos", description = "Este endpoint devuelve todos los proyectos en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyectos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Proyecto>> getAllProyecto() {
        try {
            List<Proyecto> proyectos = proyectoService.getAllProyectos();
            return new ResponseEntity<>(proyectos, HttpStatus.OK);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    //get las tareas que pertenecen al proyecto id

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Crear un nuevo proyecto",
            description = "Crea un nuevo proyecto, verifica si el cliente existe y si el proyecto ya fue creado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "El proyecto ya existe o el cliente no fue encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> crearProyecto(@RequestBody ProyectoDTO proyecto) {
        // Se delega el manejo de excepciones al GlobalExceptionHandler
        Proyecto nuevoProyecto = proyectoService.crearProyecto(proyecto);
        return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    }





    @Operation(summary = "Obtener un proyecto por su ID", description = "Este endpoint permite obtener los detalles de un proyecto mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto encontrado correctamente."),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado con el ID proporcionado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(@PathVariable Long id) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(id);
        return ResponseEntity.ok(proyecto);
    }

    //actualizacion de la tabla de estados
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/estado/{id}")
    @Operation(summary = "Cambia el estado de un proyecto", description = "Permite cambiar el estado de un proyecto en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error: El estado no es válido o el proyecto ya ha sido terminado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    public ResponseEntity<?> cambioEstado(@PathVariable int id, @RequestBody int estado) {
        try {
            ProyectoEstado proyectoActualizado = proyectoService.cambioEstado(id, estado);
            return ResponseEntity.ok(proyectoActualizado);
        } catch (ProyectoTerminadoException ex) {

            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + ex.getMessage());
        }
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza los detalles de un proyecto",
            description = "Permite modificar los detalles de un proyecto en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error: Parámetros incorrectos o proyecto ya terminado"),
                    @ApiResponse(responseCode = "404", description = "Error: Proyecto no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyectoDetalles) {
        try {
            Proyecto proyectoActualizado = proyectoService.actualizarProyecto(id, proyectoDetalles);
            return ResponseEntity.ok(proyectoActualizado);
        } catch (ProyectoNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }








    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proyecto", description = "Permite eliminar un proyecto especificando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error con los argumentos proporcionados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> eliminarProyecto(@PathVariable Long id) {
        try {
            proyectoService.eliminarProyecto(id);
            return ResponseEntity.ok("Proyecto eliminado exitosamente.");
        } catch (ProyectoNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El proyecto con ID " + id + " no fue encontrado.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                    .body("Se ha producido un error con los argumentos proporcionados.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado.");
        }
    }


}


