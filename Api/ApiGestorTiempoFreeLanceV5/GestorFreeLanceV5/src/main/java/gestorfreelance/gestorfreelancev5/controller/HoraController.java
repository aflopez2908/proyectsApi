package gestorfreelance.gestorfreelancev5.controller;


import gestorfreelance.gestorfreelancev5.DTO.RequestHorasDTO;
import gestorfreelance.gestorfreelancev5.service.HoraService;
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
}
