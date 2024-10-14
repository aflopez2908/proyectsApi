package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;

import java.sql.Date;
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
@Table(name = "Historial_Notificaciones")
public class HistorialNotificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historialId;

    @ManyToOne
    @JoinColumn(name = "notificacion_id")
    private Notificaciones notificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

/*    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;*/
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
}
