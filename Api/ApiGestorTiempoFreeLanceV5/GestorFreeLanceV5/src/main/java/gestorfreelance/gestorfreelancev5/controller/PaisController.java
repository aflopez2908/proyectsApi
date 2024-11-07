package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.model.Pais;
import gestorfreelance.gestorfreelancev5.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @PostMapping
    public ResponseEntity<Pais> createPais(@RequestBody Pais pais) {
        return new ResponseEntity<>(paisService.createPais(pais), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pais>> getAllPaises() {
        return new ResponseEntity<>(paisService.getAllPaises(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable Integer id) {
        return paisService.getPaisById(id)
                .map(pais -> new ResponseEntity<>(pais, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> updatePais(@PathVariable Integer id, @RequestBody Pais paisDetails) {
        return new ResponseEntity<>(paisService.updatePais(id, paisDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Integer id) {
        paisService.deletePais(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
