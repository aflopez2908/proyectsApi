package gestorfreelance.gestorfreelancev5.controller;


import gestorfreelance.gestorfreelancev5.service.HoraService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/horas")
@Validated // Asegura la validación de los parámetros
public class HoraController {

    @Autowired
    private HoraService horaService;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarHoras(
            @RequestParam @NotNull(message = "El ID del proyecto es obligatorio.") @Min(value = 1, message = "El ID del proyecto debe ser un número positivo.") Long proyectoId,
            @RequestParam @NotNull(message = "Las horas son obligatorias.") @Min(value = 1, message = "Las horas deben ser un número positivo.") int horas) {

        String resultado = horaService.agregarHorasABolsa(proyectoId, horas);

        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }

}
