package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;
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
@Table(name = "Historial_Notificacion")
public class HistorialNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historialId;

    @ManyToOne
    @JoinColumn(name = "notificacion_id")
    private Notificacion notificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
}
