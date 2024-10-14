package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.RespuestasEncuesta;
import gestorfreelance.gestorfreelancev5.model.RespuestasUsuarios;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RespuestasUsuariosRepository")
public interface RespuestasUsuariosRepository extends JpaRepository<RespuestasUsuarios, Integer> {
    List<RespuestasUsuarios> findByRespuesta(RespuestasEncuesta respuesta);
    List<RespuestasUsuarios> findByUsuario(Usuarios usuario);
}
