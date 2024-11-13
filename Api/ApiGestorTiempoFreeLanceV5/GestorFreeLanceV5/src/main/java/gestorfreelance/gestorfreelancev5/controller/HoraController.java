package gestorfreelance.gestorfreelancev5.controller;


import gestorfreelance.gestorfreelancev5.DTO.RequestHorasDTO;
import gestorfreelance.gestorfreelancev5.service.HoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hour")
@Validated // Asegura la validación de los parámetros
public class HoraController {

    @Autowired
    private HoraService horaService;


    @PostMapping("/add")
    public ResponseEntity<String> putHoras(@RequestBody @Validated RequestHorasDTO requestHoras) {
        String resultado = horaService.agregarHorasABolsa(requestHoras.getProyectoId(), requestHoras.getHoras());

        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }
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
}
