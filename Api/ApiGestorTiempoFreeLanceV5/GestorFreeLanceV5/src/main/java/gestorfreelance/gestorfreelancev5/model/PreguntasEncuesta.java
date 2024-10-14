package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
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
@Table(name="Preguntas_Encuesta")
public class PreguntasEncuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preguntaId;

    @ManyToOne
    @JoinColumn(name = "encuesta_id")
    private Encuestas encuesta;

    @Column(columnDefinition = "TEXT")
    private String pregunta;    
}
