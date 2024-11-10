package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Bolsa_Hora")
public class BolsaHora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bolsaId;

/*    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;*/

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @Column(nullable = false)
    private Integer horasTotales;

    @Column(nullable = false)
    private Integer horasUsadas;

    @Column(nullable = false)
    private Integer horasRestantes;   
}
