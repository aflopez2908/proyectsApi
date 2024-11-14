package gestorfreelance.gestorfreelancev5.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorasPorProyectoRequestDTO {
    @NotBlank
    private Long proyectoId;
}
