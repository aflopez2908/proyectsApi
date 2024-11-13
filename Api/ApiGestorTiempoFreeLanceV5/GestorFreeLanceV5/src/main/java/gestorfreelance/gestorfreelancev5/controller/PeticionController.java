package gestorfreelance.gestorfreelancev5.controller;


import gestorfreelance.gestorfreelancev5.DTO.PeticionDTO;
import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.ProyectoNoEncontradoException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.exception.ResourceNotFoundException;
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



    @Operation(summary = "Obtiene todos las peticiones",
            description = "Este endpoint devuelve todos las peticiones en la base de datos")
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


    @Operation(summary = "Obtener una peticion por su ID",
            description = "Este endpoint permite obtener los detalles de una peticion mediante su ID.")
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
            description = "Este endpoint permite crear una nueva petición en el sistema. El proceso sigue los siguientes pasos:\n\n" +
                    "1. **Validación de Campos**: Se valida que los campos necesarios en el cuerpo de la solicitud no sean nulos ni vacíos. " +
                    "Si alguno de los campos requeridos está vacío o no es válido, se devolverá un error `400` (Bad Request).\n\n" +
                    "2. **Verificación de Cliente**: Se verifica si el cliente proporcionado en la solicitud existe en la base de datos. " +
                    "Si el cliente no es encontrado, se devuelve un error `404` (Not Found).\n\n" +
                    "3. **Obtención del Estado Predeterminado de la Petición**: Se asigna un estado de petición predeterminado (con ID = 1). " +
                    "Si no se puede encontrar este estado, se devuelve un error `404` (Not Found).\n\n" +
                    "4. **Obtención del Tipo de Petición**: Se busca el tipo de petición según el ID proporcionado en la solicitud. " +
                    "Si el tipo de petición no existe, se devuelve un error `404` (Not Found).\n\n" +
                    "5. **Creación de la Petición**: Si todos los datos son válidos y las entidades requeridas son encontradas, " +
                    "se crea una nueva petición en la base de datos, asignando el cliente, el tipo de petición y el estado de la misma.\n\n" +
                    "6. **Registro del Estado**: Después de crear la petición, se registra el estado de la misma con una descripción que detalla la " +
                    "acción realizada (creación de la petición). Esto se realiza mediante el método `registrarEstado`.\n\n" +
                    "7. **Respuesta**: Finalmente, si la creación de la petición es exitosa, se devuelve un objeto `Peticion` con los detalles " +
                    "de la nueva petición creada y un código de estado HTTP `201` (Created). Si ocurre un error en cualquier parte del proceso, " +
                    "se devuelve el código de estado correspondiente (`400`, `404`, `500`).",

    responses = {
        @ApiResponse(responseCode = "201", description = "Petición creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error: Parámetros incorrectos o campos nulos"),
        @ApiResponse(responseCode = "404", description = "Error: Cliente, Tipo de Petición o Estado no encontrado"),
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
    @Operation(summary = "Cambia el estado de una peticion",
            description = "Este endpoint permite cambiar el estado de una petición. El proceso sigue los siguientes pasos:\n\n" +
                    "1. **Validación de Existencia de Petición**: Se verifica si la petición con el ID proporcionado existe en la base de datos. " +
                    "Si la petición no es encontrada, se lanza un error `404` (Not Found) con el mensaje correspondiente.\n\n" +
                    "2. **Validación del Estado**: Se valida si el ID del estado proporcionado es válido. " +
                    "Si el estado no es encontrado en la base de datos, se devuelve un error `400` (Bad Request) indicando que el estado es inválido.\n\n" +
                    "3. **Restricción para Cambiar a Estado 'Terminado'**: Se verifica si la petición ya se encuentra en un estado 'terminado' (ID = 2). " +
                    "Si es así, se lanza un error `400` con el mensaje de que no se puede cambiar el estado de una petición terminada.\n\n" +
                    "4. **Cambio de Estado de la Petición**: Si la petición y el estado son válidos, se procede a actualizar el estado de la petición en la base de datos.\n\n" +
                    "5. **Registro del Cambio de Estado**: Se registra el cambio de estado de la petición en el historial de estados utilizando el método `registrarEstado`.\n\n" +
                    "6. **Obtención del Historial de Estados**: Se obtiene y devuelve el historial completo de estados para la petición con el ID proporcionado.\n\n" +
                    "7. **Respuesta**: Finalmente, el servidor devuelve el historial de estados actualizado para la petición en formato JSON, " +
                    "o un error en caso de que alguna de las validaciones falle.",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Estado de la petición actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error: Estado no válido o petición en estado terminado"),
                    @ApiResponse(responseCode = "404", description = "Error: Petición no encontrada con el ID proporcionado"),
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



    @PutMapping("/{id}")
    @Operation(summary = "Actualiza los detalles de una peticion",
            description = "Permite modificar los detalles de una petición en el sistema. El flujo de esta operación es el siguiente:\n" +
                    "1. Se valida que la petición con el ID proporcionado exista.\n" +
                    "2. Se verifica que los parámetros de entrada no sean nulos y que sean válidos.\n" +
                    "3. Si la petición no está en estado 'terminado', se actualizan los detalles proporcionados (comentarios, tipo de petición, cliente, etc.).\n" +
                    "4. Si los parámetros son correctos, se guarda la petición modificada y se devuelve la respuesta exitosa.\n" +
                    "5. En caso de que no se pueda encontrar la petición o los parámetros sean inválidos, se lanza una excepción que es capturada y manejada.\n" +
                    "6. Si ocurre un error interno durante el proceso de actualización, se maneja adecuadamente con un código de error 500.",


            responses = {
                    @ApiResponse(responseCode = "200", description = "peticion actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error: Parámetros incorrectos, campos ingresados como nulos"),
                    @ApiResponse(responseCode = "404", description = "Error: Peticion no encontrada, o algun otro campo requerido no fue encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })

    public ResponseEntity<Peticion> actualizarPeticion(@PathVariable Long id, @RequestBody PeticionDTO peticion) {
        Peticion peticionActualizada = peticionService.actualizarPeticion(id, peticion);
        return ResponseEntity.ok(peticionActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una peticion", description = "Permite eliminar una peticion especificando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peticion eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "peticion no encontrada"),
            @ApiResponse(responseCode = "400", description = "Error con los argumentos proporcionados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> EliminarPeticion(@PathVariable Long id) {
        peticionService.eliminarPeticion(id);
        return ResponseEntity.ok("peticion eliminado exitosamente");

    }







}
