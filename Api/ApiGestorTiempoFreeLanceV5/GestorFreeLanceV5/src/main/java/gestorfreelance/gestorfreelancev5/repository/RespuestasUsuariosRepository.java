package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.RespuestaEncuesta;
import gestorfreelance.gestorfreelancev5.model.RespuestaUsuario;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RespuestasUsuariosRepository")
public interface RespuestasUsuariosRepository extends JpaRepository<RespuestaUsuario, Integer> {
    List<RespuestaUsuario> findByRespuesta(RespuestaEncuesta respuesta);
    List<RespuestaUsuario> findByUsuario(Usuario usuario);
}
