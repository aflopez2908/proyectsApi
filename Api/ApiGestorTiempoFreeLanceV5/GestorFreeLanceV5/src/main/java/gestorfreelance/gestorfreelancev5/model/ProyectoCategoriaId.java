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
public class ProyectoCategoriaId implements Serializable {

    private Integer proyectoId;
    private Integer categoriaId;
}
