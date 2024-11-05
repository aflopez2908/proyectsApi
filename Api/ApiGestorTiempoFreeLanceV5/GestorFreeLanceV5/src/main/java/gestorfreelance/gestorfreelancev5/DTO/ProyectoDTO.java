package gestorfreelance.gestorfreelancev5.DTO;

import gestorfreelance.gestorfreelancev5.model.Proyecto;
import lombok.Data;

@Data
public class ProyectoDTO {
    private Proyecto proyecto;
    private Integer horasAsignadas;
}
