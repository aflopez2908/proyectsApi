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
@Table(name = "intento_login")
public class IntentoLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intento_id")
    private Long intentoId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "contador", nullable = false)
    private int contador = 0;

    @Column(name = "bloqueado", nullable = false)
    private boolean bloqueado = false;

    @Column(name = "vigente", nullable = false)
    private boolean vigente = true;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
