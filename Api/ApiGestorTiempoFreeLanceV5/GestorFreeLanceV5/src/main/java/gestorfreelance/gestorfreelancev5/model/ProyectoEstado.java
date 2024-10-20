package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "Proyecto_Estado")
public class ProyectoEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proyectoEstadoId;

    @ManyToOne
    @JoinColumn(name = "proyectoId")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "estadoId")
    private EstadoProyecto estado;

    private Date fechaCambio;

    private String comentario;

    private int vigencia;


    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public Long getProyectoEstadoId() {
        return proyectoEstadoId;
    }

    public void setProyectoEstadoId(Long proyectoEstadoId) {
        this.proyectoEstadoId = proyectoEstadoId;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
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

