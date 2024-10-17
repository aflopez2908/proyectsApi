package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

/**
 *
 * @author pipel
 */
@Entity
@Table(name = "Proyecto_Estados")
public class ProyectoEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proyectoEstadoId;

    @ManyToOne
    @JoinColumn(name = "proyectoId")
    private Proyectos proyecto;

    @ManyToOne
    @JoinColumn(name = "estadoId")
    private EstadosProyecto estado;

    private Date fechaCambio;

    private String comentario;






    public Long getProyectoEstadoId() {
        return proyectoEstadoId;
    }

    public void setProyectoEstadoId(Long proyectoEstadoId) {
        this.proyectoEstadoId = proyectoEstadoId;
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

    public EstadosProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadosProyecto estado) {
        this.estado = estado;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


}

