package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;

@Entity
@Table(name = "peticion")
public class Peticion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peticion")
    private Long idPeticion;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "cliente_id")
    private Cliente cliente;  // Relación ManyToOne con Cliente

    @ManyToOne
    @JoinColumn(name = "id_tipo_peticion", referencedColumnName = "it_tipo_peticion")
    private TipoPeticion tipoPeticion; // Relación ManyToOne con TipoPeticion

    @Column(name = "comentario_peticion")
    private String comentarioPeticion;




    public Long getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(Long idPeticion) {
        this.idPeticion = idPeticion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoPeticion getTipoPeticion() {
        return tipoPeticion;
    }

    public void setTipoPeticion(TipoPeticion tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    public String getComentarioPeticion() {
        return comentarioPeticion;
    }

    public void setComentarioPeticion(String comentarioPeticion) {
        this.comentarioPeticion = comentarioPeticion;
    }
}
