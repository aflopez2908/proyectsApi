package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_peticion")
public class TipoPeticion {

    @Id
    @Column(name = "it_tipo_peticion")
    private Long idTipoPeticion; // Usamos Long como tipo de dato

    @Column(name = "descripcion_peticion")
    private String descripcionPeticion;

    @Column(name = "descripcion")
    private String descripcion;

    // Getters y Setters
    public Long getItTipoPeticion() {
        return idTipoPeticion;
    }

    public void setItTipoPeticion(Long itTipoPeticion) {
        this.idTipoPeticion = itTipoPeticion;
    }

    public String getDescripcionPeticion() {
        return descripcionPeticion;
    }

    public void setDescripcionPeticion(String descripcionPeticion) {
        this.descripcionPeticion = descripcionPeticion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
