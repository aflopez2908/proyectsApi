package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProyectosTiposId {
     private Integer proyectoId;
    private Integer tipoId;   
}
