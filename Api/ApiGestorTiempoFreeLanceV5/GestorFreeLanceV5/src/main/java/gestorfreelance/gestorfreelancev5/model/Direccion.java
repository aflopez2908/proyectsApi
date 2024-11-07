package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direccion_id")
    private Integer direccionId;
    
    @Column(length = 255)
    private String calle;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;
}
