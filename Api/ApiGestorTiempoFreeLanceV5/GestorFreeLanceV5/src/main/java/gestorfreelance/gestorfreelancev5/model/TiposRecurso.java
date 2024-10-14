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
@Table(name = "Tipos_Recurso")
public class TiposRecurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipoId;
    @Column(nullable = false, length = 50)
    private String nombre;
}
