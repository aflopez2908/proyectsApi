package gestorfreelance.gestorfreelancev5.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRestController {
    @GetMapping("/saludo")
    public String saluso() {
        System.out.println("Hola Mundo Inicio");
        return "Hola Mundo test Inicio";
    }
}
