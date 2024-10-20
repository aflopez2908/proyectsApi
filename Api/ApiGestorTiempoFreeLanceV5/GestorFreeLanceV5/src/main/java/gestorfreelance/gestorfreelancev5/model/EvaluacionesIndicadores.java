package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name="Evaluaciones_Indicadores")
public class EvaluacionesIndicadores {

    @EmbeddedId
    private EvaluacionesIndicadoresId id;

    @ManyToOne
    @MapsId("evaluacionId")
    @JoinColumn(name = "evaluacion_id")
    private EvaluacionesDesempeno evaluacion;

    @ManyToOne
    @MapsId("indicadorId")
    @JoinColumn(name = "indicador_id")
    private IndicadoresDesempeno indicador;
    @Column
    private Double valor;    
}
