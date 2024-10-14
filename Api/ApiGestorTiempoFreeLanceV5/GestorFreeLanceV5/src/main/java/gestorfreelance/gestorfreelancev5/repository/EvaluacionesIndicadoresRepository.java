package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.EvaluacionesIndicadoresId;
import gestorfreelance.gestorfreelancev5.model.EvaluacionesDesempeno;
import gestorfreelance.gestorfreelancev5.model.EvaluacionesIndicadores;
import gestorfreelance.gestorfreelancev5.model.IndicadoresDesempeno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("EvaluacionesIndicadoresRepository")
public interface EvaluacionesIndicadoresRepository extends JpaRepository<EvaluacionesIndicadores, EvaluacionesIndicadoresId> {
    List<EvaluacionesIndicadores> findByEvaluacion(EvaluacionesDesempeno evaluacion);
    List<EvaluacionesIndicadores> findByIndicador(IndicadoresDesempeno indicador);
}
