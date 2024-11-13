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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
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
        // Delegamos la gestión de excepciones a la clase GlobalExceptionHandler
        List<Proyecto> proyectos = proyectoService.getAllProyectos();
        return ResponseEntity.ok(proyectos);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(
            summary = "Crear Proyecto",
            description = """
            Crea un nuevo proyecto en el sistema.
            El flujo del servicio de creación de proyecto es el siguiente:
            1. Verifica que la fecha de inicio no sea posterior a la fecha de fin; si lo es, lanza una excepción.
            2. Asigna un nuevo ID al proyecto para evitar conflictos.
            3. Valida que los campos requeridos en el objeto ProyectoDTO no sean nulos o vacíos.
            4. Crea un nuevo objeto Proyecto, establece sus campos según el DTO recibido, y asigna un cliente 
               existente, o lanza una excepción si el cliente no existe.
            5. Verifica si ya existe un proyecto con el mismo nombre en la base de datos. Si es así, lanza 
               una excepción para evitar duplicados.
            6. Guarda el proyecto en la base de datos.
            7. Crea una instancia de BolsaHora para el proyecto, inicializa sus valores de horas asignadas y 
               restantes, y guarda la BolsaHora.
            8. Envía un correo electrónico de bienvenida al cliente con los detalles del proyecto.
            9. Registra el estado inicial del proyecto, llamado "Creación del proyecto", en la base de datos.
            """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proyecto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Proyecto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Proyecto ya existente",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
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
    @Operation(
            summary = "Cambiar el estado de un proyecto",
            description = """
            Cambia el estado de un proyecto existente.
            Flujo de cambio de estado:
            1. Se verifica si el proyecto existe en el repositorio. Si no existe, se lanza `ResourceNotFoundException`.
            2. Se valida que el estado proporcionado exista en el repositorio `EstadoProyecto`. Si el estado es inválido, 
               se lanza `IllegalArgumentException`.
            3. Se verifica si el proyecto ya está terminado. Si está en estado terminado, se lanza `ProyectoTerminadoException`.
            4. Se valida que el nuevo estado sea coherente con el flujo de estados permitidos.
            5. Si el nuevo estado es válido, se actualiza la vigencia del estado actual.
            6. Se registra el nuevo estado con un comentario de actualización.
            7. Retorna el objeto `ProyectoEstado` con el estado actualizado del proyecto.
            """,
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "ID del proyecto a actualizar", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "estado", description = "Nuevo estado del proyecto", required = true)
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado del proyecto actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProyectoEstado.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Estado inválido o conflicto de estado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "El proyecto ya está terminado y no puede cambiar de estado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> cambioEstado(@PathVariable int id, @RequestBody int estado) {
        ProyectoEstado proyectoActualizado = proyectoService.cambioEstado(id, estado);
        return ResponseEntity.ok(proyectoActualizado);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar Proyecto",
            description = """
            Actualiza los detalles de un proyecto existente.
            Flujo de actualización del proyecto:
            1. Verifica que el proyecto exista. Si no, lanza una excepción `ProyectoNoEncontradoException`.
            2. Consulta el estado del proyecto para confirmar si está terminado. Si el proyecto está terminado, 
               lanza una excepción `ProyectoTerminadoException` y no permite su modificación.
            3. Valida que los campos del proyecto no sean nulos o vacíos.
            4. Comprueba si el nombre del proyecto está en uso. Si ya existe, lanza una excepción.
            5. Verifica si el nombre del proyecto ha cambiado. Si es así, valida que el nuevo nombre sea único.
            6. Actualiza los detalles del proyecto con los nuevos valores.
            7. Guarda el proyecto en la base de datos y devuelve el proyecto actualizado.
            """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Proyecto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o nombre en uso",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "El proyecto ya existe y no se puede crear",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "El proyecto ya está terminado y no puede actualizarse",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @RequestBody ProyectoDTO proyectoDetalles) {
        Proyecto proyectoActualizado = proyectoService.actualizarProyecto(id, proyectoDetalles);
        return ResponseEntity.ok(proyectoActualizado);
    }




    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un proyecto",
            description = """
            Elimina un proyecto especificado por su ID.
            Flujo de eliminación del proyecto:
            1. Se verifica si el proyecto existe en la base de datos. Si no se encuentra, se lanza `ProyectoNoEncontradoException`.
            2. Si el proyecto existe, se eliminan las relaciones asociadas al proyecto para evitar errores de integridad referencial.
            3. Se elimina el proyecto de la base de datos mediante `proyectoRepository.delete`.
            """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Proyecto eliminado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<String> eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.ok("Proyecto eliminado exitosamente.");
    }

}


