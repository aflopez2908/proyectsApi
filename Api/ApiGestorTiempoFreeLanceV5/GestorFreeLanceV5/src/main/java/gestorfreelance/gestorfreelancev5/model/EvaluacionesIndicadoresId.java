package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EvaluacionesIndicadoresId implements Serializable{
    private Integer evaluacionId;
    private Integer indicadorId;    
}
