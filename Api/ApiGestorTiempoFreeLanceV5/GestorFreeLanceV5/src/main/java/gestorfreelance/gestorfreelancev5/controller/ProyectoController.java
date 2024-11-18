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
import io.swagger.v3.oas.annotations.Parameter;
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


/**
 * Controlador para gestionar proyectos.
 * Este controlador expone los endpoints para realizar operaciones CRUD y manejar
 * estados de proyectos dentro del sistema.
 */
@RestController
@RequestMapping("/api/v1/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    /**
     * Obtiene todos los proyectos registrados en la base de datos.
     *
     * @return Lista de proyectos.
     */
    @Operation(
            summary = "Obtiene todos los proyectos",
            description = "Este endpoint devuelve todos los proyectos almacenados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proyectos obtenidos exitosamente.",
                    content = @Content(schema = @Schema(implementation = Proyecto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping
    public ResponseEntity<List<Proyecto>> Proyectos() {
        List<Proyecto> proyectos = proyectoService.getAllProyectos();
        return ResponseEntity.ok(proyectos);
    }

    /**
     * Crea un nuevo proyecto en el sistema.
     *
     * @param proyectoDTO Datos del proyecto a crear.
     * @return El proyecto creado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(
            summary = "Crear un proyecto",
            description = """
            Crea un nuevo proyecto con los datos proporcionados en el cuerpo de la solicitud.
            Realiza validaciones para evitar conflictos y asegurar la integridad del proyecto.
            """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente.",
                    content = @Content(schema = @Schema(implementation = Proyecto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Proyecto ya existente.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Proyecto> Proyectos(@RequestBody ProyectoDTO proyectoDTO) {
        Proyecto nuevoProyecto = proyectoService.crearProyecto(proyectoDTO);
        return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    }

    /**
     * Obtiene los detalles de un proyecto por su ID.
     *
     * @param id ID del proyecto a buscar.
     * @return Detalles del proyecto encontrado.
     */
    @Operation(
            summary = "Obtener un proyecto por su ID",
            description = "Devuelve los detalles del proyecto especificado mediante su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proyecto encontrado correctamente.",
                    content = @Content(schema = @Schema(implementation = Proyecto.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado con el ID proporcionado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> Proyectos(
            @Parameter(description = "ID del proyecto a buscar.", required = true)
            @PathVariable Long id) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(id);
        return ResponseEntity.ok(proyecto);
    }

    /**
     * Actualiza los detalles de un proyecto.
     *
     * @param id              ID del proyecto a actualizar.
     * @param proyectoDetalles Nuevos detalles del proyecto.
     * @return Proyecto actualizado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un proyecto",
            description = "Actualiza los detalles de un proyecto existente en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente.",
                    content = @Content(schema = @Schema(implementation = Proyecto.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflicto en la actualización del proyecto.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Proyecto> Proyectos(
            @Parameter(description = "ID del proyecto a actualizar.", required = true)
            @PathVariable Long id,
            @RequestBody ProyectoDTO proyectoDetalles) {
        Proyecto proyectoActualizado = proyectoService.actualizarProyecto(id, proyectoDetalles);
        return ResponseEntity.ok(proyectoActualizado);
    }

    /**
     * Cambia el estado de un proyecto.
     *
     * @param id     ID del proyecto a actualizar.
     * @param estado Nuevo estado del proyecto.
     * @return Estado del proyecto actualizado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/estado/{id}")
    @Operation(
            summary = "Cambiar el estado de un proyecto",
            description = "Actualiza el estado de un proyecto con las validaciones correspondientes."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado del proyecto actualizado exitosamente.",
                    content = @Content(schema = @Schema(implementation = ProyectoEstado.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ProyectoEstado> changeEstado(
            @Parameter(description = "ID del proyecto.", required = true)
            @PathVariable int id,
            @RequestBody int estado) {
        ProyectoEstado proyectoActualizado = proyectoService.cambioEstado(id, estado);
        return ResponseEntity.ok(proyectoActualizado);
    }

    /**
     * Elimina un proyecto por su ID.
     *
     * @param id ID del proyecto a eliminar.
     * @return Mensaje de éxito.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un proyecto",
            description = "Elimina un proyecto especificado por su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Proyecto eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<String> Proyecto(
            @Parameter(description = "ID del proyecto a eliminar.", required = true)
            @PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.ok("Proyecto eliminado exitosamente.");
    }
}