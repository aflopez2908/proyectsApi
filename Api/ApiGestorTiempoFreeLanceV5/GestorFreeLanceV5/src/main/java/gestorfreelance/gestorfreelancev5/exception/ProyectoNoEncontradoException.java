package gestorfreelance.gestorfreelancev5.exception;

public class ProyectoNoEncontradoException extends RuntimeException {
    public ProyectoNoEncontradoException(Long id) {
        super("No se encontró el proyecto con ID: " + id);
    }
}
