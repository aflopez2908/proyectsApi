package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.PreguntaEncuesta;
import gestorfreelance.gestorfreelancev5.model.RespuestaEncuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RespuestasEncuestaRepository")
public interface RespuestasEncuestaRepository extends JpaRepository<RespuestaEncuesta, Integer> {
    List<RespuestaEncuesta> findByPregunta(PreguntaEncuesta pregunta);
    List<RespuestaEncuesta> findByRespuestaContaining(String respuesta);
}
