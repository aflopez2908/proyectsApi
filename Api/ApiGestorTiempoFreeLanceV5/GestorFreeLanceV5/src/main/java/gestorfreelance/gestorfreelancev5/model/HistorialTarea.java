package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Historial_Tarea")
public class HistorialTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID historialId;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;

    @Column(columnDefinition = "TEXT")
    private String cambio;

    @Column(name = "fecha_cambio")
    private LocalDateTime fechaCambio;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(columnDefinition = "vigente")
    private Integer vigente ;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoTarea estadoTarea;

}
