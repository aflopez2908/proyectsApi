package gestorfreelance.gestorfreelancev5.DTO;

public class PeticionDTO {

    private Long idCliente;  // id del cliente en lugar de objeto Cliente
    private Long idTipoPeticion;  // id del tipo de petición en lugar de objeto TipoPeticion
    private String comentarioPeticion;

    // Getters y Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdTipoPeticion() {
        return idTipoPeticion;
    }

    public void setIdTipoPeticion(Long idTipoPeticion) {
        this.idTipoPeticion = idTipoPeticion;
    }

    public String getComentarioPeticion() {
        return comentarioPeticion;
    }

    public void setComentarioPeticion(String comentarioPeticion) {
        this.comentarioPeticion = comentarioPeticion;
    }
}
