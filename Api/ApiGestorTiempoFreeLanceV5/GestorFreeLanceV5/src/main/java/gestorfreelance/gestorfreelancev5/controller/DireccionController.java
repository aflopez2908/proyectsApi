package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.model.Direccion;
import gestorfreelance.gestorfreelancev5.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/direccion")
public class DireccionController {
    @Autowired
    private DireccionService direccionService;

    @PostMapping
    public ResponseEntity<Direccion> createDireccion(@RequestBody Direccion direccion) {
        return new ResponseEntity<>(direccionService.createDireccion(direccion), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Direccion>> getAllDirecciones() {
        return new ResponseEntity<>(direccionService.getAllDirecciones(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable Integer id) {
        return direccionService.getDireccionById(id)
                .map(direccion -> new ResponseEntity<>(direccion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> updateDireccion(@PathVariable Integer id, @RequestBody Direccion direccionDetails) {
        return new ResponseEntity<>(direccionService.updateDireccion(id, direccionDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDireccion(@PathVariable Integer id) {
        direccionService.deleteDireccion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
