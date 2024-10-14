package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Encuestas;
import gestorfreelance.gestorfreelancev5.model.PreguntasEncuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PreguntasEncuestaRepository")
public interface PreguntasEncuestaRepository extends JpaRepository<PreguntasEncuesta, Integer> {
    List<PreguntasEncuesta> findByEncuesta(Encuestas encuesta);
    List<PreguntasEncuesta> findByPreguntaContaining(String pregunta);
}
