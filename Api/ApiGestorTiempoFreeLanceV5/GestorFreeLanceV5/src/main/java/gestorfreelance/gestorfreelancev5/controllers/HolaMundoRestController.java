package gestorfreelance.gestorfreelancev5.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRestController {
    @GetMapping("/saludo")
    public String saluso() {
        System.out.println("Hola Mundo Inicio 4:46");
        return "Hola Mundo test Inicio 4:46";
    }
}
