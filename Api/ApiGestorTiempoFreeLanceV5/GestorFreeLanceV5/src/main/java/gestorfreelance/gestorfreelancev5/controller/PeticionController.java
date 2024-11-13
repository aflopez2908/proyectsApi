package gestorfreelance.gestorfreelancev5.controller;


import gestorfreelance.gestorfreelancev5.DTO.PeticionDTO;
import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.model.Peticion;
import gestorfreelance.gestorfreelancev5.model.PeticionEstado;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import gestorfreelance.gestorfreelancev5.service.PeticionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/peticion"})
public class PeticionController {

    @Autowired
    PeticionService peticionService;



    @Operation(summary = "Obtiene todos las peticiones", description = "Este endpoint devuelve todos las petciones en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Petciones obtenidas exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Peticion>> getAllPeticiones() {
        try {
            List<Peticion> peticiones = peticionService.getAllPeticiones();
            return new ResponseEntity<>(peticiones, HttpStatus.OK);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @Operation(summary = "Obtener una peticion por su ID", description = "Este endpoint permite obtener los detalles de una peticion mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peticion encontrada correctamente."),
            @ApiResponse(responseCode = "404", description = "Peticion no encontrada con el ID proporcionado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Peticion> obtenerProyectoPorId(@PathVariable Long id) {
        Peticion peticion = peticionService.obtenerPeticionPorId(id);
        return ResponseEntity.ok(peticion);
    }


    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    @Operation(summary = "Crear una peticion",
            description = "Crea una nueva peticion, verifica si el cliente existe y si los campos son correctos apra la creacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Peticion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "La peticion ya existe o el cliente no fue encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> crearPeticion(@RequestBody PeticionDTO peticion) {
        // Se delega el manejo de excepciones al GlobalExceptionHandler
        Peticion nuevaPeticion= peticionService.crearPeticion(peticion);
        return new ResponseEntity<>(nuevaPeticion, HttpStatus.CREATED);
    }

    //actualizacion de la tabla de estados
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/estado/{id}")
    @Operation(summary = "Cambia el estado de una peticion", description = "Permite cambiar el estado de una peticion en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Peeticion actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error: El estado no es válido o la peticion ya ha sido terminada"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    public ResponseEntity<?> cambioEstado(@PathVariable int id, @RequestBody int estado) {
        try {
            List<PeticionEstado> peticionEstado = peticionService.cambioEstado(id, estado);
            return ResponseEntity.ok(peticionEstado);
        } catch (ProyectoTerminadoException ex) {

            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + ex.getMessage());
        }
    }













}
