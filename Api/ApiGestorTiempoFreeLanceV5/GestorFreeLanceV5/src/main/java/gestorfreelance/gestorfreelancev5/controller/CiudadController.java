package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.model.Ciudad;
import gestorfreelance.gestorfreelancev5.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @PostMapping
    public ResponseEntity<Ciudad> createCiudad(@RequestBody Ciudad ciudad) {
        return new ResponseEntity<>(ciudadService.createCiudad(ciudad), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> getAllCiudades() {
        return new ResponseEntity<>(ciudadService.getAllCiudades(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getCiudadById(@PathVariable Integer id) {
        return ciudadService.getCiudadById(id)
                .map(ciudad -> new ResponseEntity<>(ciudad, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> updateCiudad(@PathVariable Integer id, @RequestBody Ciudad ciudadDetails) {
        return new ResponseEntity<>(ciudadService.updateCiudad(id, ciudadDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Integer id) {
        ciudadService.deleteCiudad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
