/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pipel
 */
@RestController
public class HolaMundoController {

    @GetMapping(path="/hola")
    public String decirHola() {
        return "Hola Mundo";
    }
}
