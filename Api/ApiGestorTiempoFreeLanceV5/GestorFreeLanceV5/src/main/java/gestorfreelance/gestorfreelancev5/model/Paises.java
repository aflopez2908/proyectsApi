package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="Paises")
public class Paises {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paisId;
    
    @Column(nullable = false, length = 100)
    private String nombre;    
}
