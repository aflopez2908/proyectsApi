package gestorfreelance.gestorfreelancev5.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTareaRequestDTO {
    @NotBlank
    private Long tareaId;
    private String descripcionCambio;
    private int nuevaPrioridad;
    private int nuevoEstado;
    @NotBlank
    private Long usuarioId;
}
