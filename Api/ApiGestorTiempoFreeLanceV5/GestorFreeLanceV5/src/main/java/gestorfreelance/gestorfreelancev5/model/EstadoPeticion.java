package gestorfreelance.gestorfreelancev5.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "estados_peticion")
public class EstadoPeticion {

    @jakarta.persistence.Id
    @Id
    @Column(name= "estado")
    private Long idEstado;

    @Column(name = "nombre_estado")
    private String nombreEstado;


    // Getters y Setters

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public Long getIdEstado() {
        return idEstado;
    }
}
