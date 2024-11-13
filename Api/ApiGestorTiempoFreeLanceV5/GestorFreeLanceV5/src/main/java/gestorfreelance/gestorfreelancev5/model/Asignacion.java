package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;

@Entity
@Table(name = "asignacion")
public class Asignacion {

    @Id
    private Long asignacionId; // Usamos Long como tipo de dato

    @ManyToOne
    @JoinColumn(name = "usuario_asignado", referencedColumnName = "usuario_id")
    private Usuario usuarioAsignado;

    @ManyToOne
    @JoinColumn(name = "id_peticion", referencedColumnName = "id_peticion")
    private Peticion peticion;

    // Getters y Setters
    public Long getAsignacionId() {
        return asignacionId;
    }

    public void setAsignacionId(Long asignacionId) {
        this.asignacionId = asignacionId;
    }

    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }
}
