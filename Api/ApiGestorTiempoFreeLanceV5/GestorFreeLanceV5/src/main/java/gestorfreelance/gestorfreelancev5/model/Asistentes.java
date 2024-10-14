package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Asistentes")
public class Asistentes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer asistenteId;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Eventos evento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;    
}
