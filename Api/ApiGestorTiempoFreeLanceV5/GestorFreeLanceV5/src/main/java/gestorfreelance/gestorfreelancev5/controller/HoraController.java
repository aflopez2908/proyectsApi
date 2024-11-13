package gestorfreelance.gestorfreelancev5.controller;


import gestorfreelance.gestorfreelancev5.DTO.HorasPorProyectoRequestDTO;
import gestorfreelance.gestorfreelancev5.DTO.HorasPorUsuarioRangoRequestDTO;
import gestorfreelance.gestorfreelancev5.DTO.HorasPorUsuarioRequestDTO;
import gestorfreelance.gestorfreelancev5.DTO.RequestHorasDTO;
import gestorfreelance.gestorfreelancev5.service.HoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/hour")
@Validated
@Tag(name = "Hour Management", description = "Operaciones relacionadas con la gestión de horas de trabajo")
public class HoraController {

    @Autowired
    private HoraService horaService;

    @Operation(summary = "Add hours to a project", description = "Agrega horas a la bolsa de horas de un proyecto")
    @PostMapping("/add")
    public ResponseEntity<String> putHoras(@RequestBody @Validated RequestHorasDTO requestHoras) {
        String resultado = horaService.agregarHorasABolsa(requestHoras.getProyectoId(), requestHoras.getHoras());

        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }
    @Operation(summary = "Create a new hour bag for a project", description = "Crea una nueva bolsa de horas para un proyecto")
    @PostMapping("/create")
    public ResponseEntity<String> crearBolsaHoras(@RequestBody @Validated RequestHorasDTO requestHoras) {
        String resultado = horaService.crearBolsaHoras(requestHoras.getProyectoId(), requestHoras.getHoras());

        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }

    @Operation(summary = "Consume horas de un proyecto", description = "Permite consumir horas de la bolsa de horas de un proyecto específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horas consumidas exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/consumir/{proyectoId}")
    public ResponseEntity<String> consumirHoras(@PathVariable int proyectoId, @RequestBody int horasConsumidas) {
        String resultado = horaService.consumirHoras(proyectoId, horasConsumidas);
        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Get total hours worked by a user", description = "Obtiene el total de horas trabajadas por un usuario")
    @PostMapping("/usuario")
    public ResponseEntity<Map<String, Integer>> getHorasPorUsuario(@RequestBody HorasPorUsuarioRequestDTO request) {
        int totalHoras = horaService.getTotalHorasPorUsuario(request.getUsuarioId());
        Map<String, Integer> response = new HashMap<>();
        response.put("totalHoras", totalHoras);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "Get total hours used by a project", description = "Obtiene el total de horas usadas en un proyecto")
    @PostMapping("/proyecto")
    public ResponseEntity<Map<String, Integer>> getHorasUsadasPorProyecto(@RequestBody HorasPorProyectoRequestDTO request) {
        int totalHorasUsadas = horaService.getTotalHorasUsadasPorProyecto(request.getProyectoId());
        Map<String, Integer> response = new HashMap<>();
        response.put("totalHorasUsadas", totalHorasUsadas);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "Get remaining hours for a project", description = "Obtiene las horas restantes de un proyecto")
    @PostMapping("/proyecto/restantes")
    public ResponseEntity<Map<String, Integer>> getHorasRestantesPorProyecto(@RequestBody HorasPorProyectoRequestDTO request) {
        int horasRestantes = horaService.getHorasRestantesPorProyecto(request.getProyectoId());
        Map<String, Integer> response = new HashMap<>();
        response.put("horasRestantes", horasRestantes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "Get total hours worked by a user within a date range", description = "Obtiene el total de horas trabajadas por un usuario en un rango de fechas")
    @PostMapping("/usuario/rango")
    public ResponseEntity<Map<String, Integer>> getHorasPorUsuarioYRangoDeFechas(@RequestBody HorasPorUsuarioRangoRequestDTO request) {
        LocalDateTime inicio = LocalDateTime.parse(request.getFechaInicio());
        LocalDateTime fin = LocalDateTime.parse(request.getFechaFin());
        int totalHoras = horaService.getHorasPorUsuarioYRangoDeFechas(request.getUsuarioId(), inicio, fin);
        Map<String, Integer> response = new HashMap<>();
        response.put("totalHoras", totalHoras);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "Get global statistics of hours", description = "Obtiene estadísticas globales sobre las horas trabajadas, restantes y total de proyectos y tareas")
    @GetMapping("/globales")
    public ResponseEntity<Map<String, Object>> getEstadisticasGlobales() {
        Map<String, Object> estadisticas = horaService.getEstadisticasGlobales();
        return new ResponseEntity<>(estadisticas, HttpStatus.OK);
    }
}
