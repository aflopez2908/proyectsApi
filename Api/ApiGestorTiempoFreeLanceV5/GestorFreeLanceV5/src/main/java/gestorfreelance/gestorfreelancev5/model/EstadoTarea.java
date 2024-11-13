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
@Table(name="Estado_Tarea")
public class EstadoTarea {
    @Id
    @Column(name = "estado_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estadoId;
    
    @Column(nullable = false, length = 50)
    private String nombre;    
}
