package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.EvaluacionIndicadorId;
import gestorfreelance.gestorfreelancev5.model.EvaluacionDesempeno;
import gestorfreelance.gestorfreelancev5.model.EvaluacionIndicador;
import gestorfreelance.gestorfreelancev5.model.IndicadorDesempeno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("EvaluacionesIndicadoresRepository")
public interface EvaluacionesIndicadoresRepository extends JpaRepository<EvaluacionIndicador, EvaluacionIndicadorId> {
    List<EvaluacionIndicador> findByEvaluacion(EvaluacionDesempeno evaluacion);
    List<EvaluacionIndicador> findByIndicador(IndicadorDesempeno indicador);
}
