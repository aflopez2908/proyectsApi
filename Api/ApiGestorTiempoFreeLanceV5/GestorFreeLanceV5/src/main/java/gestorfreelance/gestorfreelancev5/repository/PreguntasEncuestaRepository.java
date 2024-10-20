package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Encuesta;
import gestorfreelance.gestorfreelancev5.model.PreguntaEncuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PreguntasEncuestaRepository")
public interface PreguntasEncuestaRepository extends JpaRepository<PreguntaEncuesta, Integer> {
    List<PreguntaEncuesta> findByEncuesta(Encuesta encuesta);
    List<PreguntaEncuesta> findByPreguntaContaining(String pregunta);
}
