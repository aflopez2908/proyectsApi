package gestorfreelance.gestorfreelancev5.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProyectoDTO {

    private Integer proyectoId;
    private String nombre;
    private String descripcion;
    private Integer clienteId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;


    private Integer horasAsignadas;

    // Constructor que mapea los campos desde un objeto Proyecto
    public ProyectoDTO(Proyecto proyecto, Integer horasAsignadas) {
        this.proyectoId = proyecto.getProyectoId();
        this.nombre = proyecto.getNombre();
        this.descripcion = proyecto.getDescripcion();
        this.clienteId = proyecto.getCliente() != null ? proyecto.getCliente().getClienteId() : null; // Mapea solo el ID de cliente
        this.fechaInicio = proyecto.getFechaInicio();
        this.fechaFin = proyecto.getFechaFin();
        this.horasAsignadas = horasAsignadas;
    }


}

