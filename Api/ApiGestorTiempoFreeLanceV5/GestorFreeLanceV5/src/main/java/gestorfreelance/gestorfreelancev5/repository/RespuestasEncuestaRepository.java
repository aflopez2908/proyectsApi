package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.PreguntasEncuesta;
import gestorfreelance.gestorfreelancev5.model.RespuestasEncuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RespuestasEncuestaRepository")
public interface RespuestasEncuestaRepository extends JpaRepository<RespuestasEncuesta, Integer> {
    List<RespuestasEncuesta> findByPregunta(PreguntasEncuesta pregunta);
    List<RespuestasEncuesta> findByRespuestaContaining(String respuesta);
}
