package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Proyecto_Estado")
public class ProyectoEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proyecto_estado_id")
    private Long proyectoEstadoId;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoProyecto estado;

    @Column(name = "fecha_cambio")
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

