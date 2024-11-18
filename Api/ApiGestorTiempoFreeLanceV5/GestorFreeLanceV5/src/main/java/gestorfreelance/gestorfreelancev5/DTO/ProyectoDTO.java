package gestorfreelance.gestorfreelancev5.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para la transferencia de datos de Proyecto.
 */
@Data
@NoArgsConstructor
public class ProyectoDTO {

    @Schema(description = "Identificador único del proyecto", example = "1")
    private Integer proyectoId;

    @Schema(description = "Nombre del proyecto", example = "Gestión de Freelance")
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no debe superar los 100 caracteres.")
    private String nombre;

    @Schema(description = "Descripción detallada del proyecto", example = "Un sistema para gestionar proyectos freelance.")
    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres.")
    private String descripcion;

    @Schema(description = "Identificador único del cliente asociado", example = "5")
    @NotNull(message = "El cliente ID no puede ser nulo.")
    private Integer clienteId;

    @Schema(description = "Fecha de inicio del proyecto", example = "2024-01-01T09:00:00")
    @NotNull(message = "La fecha de inicio no puede ser nula.")
    private LocalDateTime fechaInicio;

    @Schema(description = "Fecha de finalización del proyecto", example = "2024-12-31T18:00:00")
    @Future(message = "La fecha de finalización debe ser en el futuro.")
    private LocalDateTime fechaFin;

    @Schema(description = "Número de horas asignadas al proyecto", example = "120")
    @NotNull(message = "Las horas asignadas no pueden ser nulas.")
    @Positive(message = "Las horas asignadas deben ser mayores a 0.")
    private Integer horasAsignadas;

    /**
     * Constructor que mapea los campos desde un objeto Proyecto.
     *
     * @param proyecto       Objeto Proyecto desde el que se toman los datos.
     * @param horasAsignadas Número de horas asignadas al proyecto.
     */
    public ProyectoDTO(Proyecto proyecto, Integer horasAsignadas) {
        this.proyectoId = proyecto.getProyectoId();
        this.nombre = proyecto.getNombre();
        this.descripcion = proyecto.getDescripcion();
        this.clienteId = proyecto.getCliente() != null ? proyecto.getCliente().getClienteId() : null;
        this.fechaInicio = proyecto.getFechaInicio();
        this.fechaFin = proyecto.getFechaFin();
        this.horasAsignadas = horasAsignadas;
    }
}
