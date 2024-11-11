package gestorfreelance.gestorfreelancev5.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestHorasDTO {
    @NotNull(message = "El ID del proyecto es obligatorio.")
    @Min(value = 1, message = "El ID del proyecto debe ser un número positivo.")
    private Integer proyectoId;

    @NotNull(message = "Las horas son obligatorias.")
    @Min(value = 1, message = "Las horas deben ser un número positivo.")
    private Integer horas;
}
