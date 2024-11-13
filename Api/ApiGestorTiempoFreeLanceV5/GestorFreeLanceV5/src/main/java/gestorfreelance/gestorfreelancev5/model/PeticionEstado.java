package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "peticion_estado")
public class PeticionEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peticion_estado_id")
    private Long peticionEstadoId;


    @ManyToOne
    @JoinColumn(name = "peticion_id", referencedColumnName = "id_peticion")
    private Peticion peticion;

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "estado")
    private EstadoPeticion estadoPeticion;

    @Column(name = "fecha_cambio")
    private LocalDate fechaCambio;
    private String comentario;
    private Integer vigencia;

    // Getters y Setters
    public Long getPeticionEstadoId() {
        return peticionEstadoId;
    }

    public void setPeticionEstadoId(Long peticionEstadoId) {
        this.peticionEstadoId = peticionEstadoId;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }

    public EstadoPeticion getEstadoPeticion() {
        return estadoPeticion;
    }

    public void setEstadoPeticion(EstadoPeticion estadoPeticion) {
        this.estadoPeticion = estadoPeticion;
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }
}
