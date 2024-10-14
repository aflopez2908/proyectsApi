package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Date;
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
@Table(name="Historial_Tareas")
public class HistorialTareas {
  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID historialId;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tareas tarea;

    @Column(columnDefinition = "TEXT")
    private String cambio;

/*    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;*/
@Column(name = "fecha_cambio")
private LocalDateTime fechaCambio;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;   
}
