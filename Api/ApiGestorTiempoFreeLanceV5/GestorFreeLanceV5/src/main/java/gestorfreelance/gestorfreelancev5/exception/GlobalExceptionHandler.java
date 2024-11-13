package gestorfreelance.gestorfreelancev5.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.BindException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Puedes agregar manejadores específicos de otras excepciones si es necesario
    @ExceptionHandler(ProyectoNoEncontradoException.class)
    public ResponseEntity<String> handleProyectoNoEncontradoException(ProyectoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Recurso no encontrado descrito como " + ex.getMessage());
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Acceso denegado: " + ex.getMessage());
    }





    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Argumento inválido: " + ex.getMessage());
    }

    // Manejo de excepción cuando hay un error de validación
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleBindException(BindException ex) {
        // Retorna un error 400 (Bad Request) cuando hay un error en los datos de entrada
        return new ResponseEntity<>("Error en los datos proporcionados: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepción cuando un proyecto ya existe
    @ExceptionHandler(ProyectoExistenteException.class)
    public ResponseEntity<String> handleProyectoExistenteException(ProyectoExistenteException ex) {
        // Retorna un error 400 (Bad Request) con el mensaje de la excepción
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepción cuando no se encuentra el cliente
    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<String> handleClienteNoEncontradoException(ClienteNoEncontradoException ex) {
        // Retorna un error 404 (Not Found) con el mensaje de la excepción
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return new ResponseEntity<>("Error de integridad en los datos: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseException(DataAccessException ex) {
        // Aquí puedes loguear el error si es necesario
        return new ResponseEntity<>("Error al acceder a la base de datos: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Maneja todas las excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Se produjo un error interno:"+ex.getMessage());
    }






}
