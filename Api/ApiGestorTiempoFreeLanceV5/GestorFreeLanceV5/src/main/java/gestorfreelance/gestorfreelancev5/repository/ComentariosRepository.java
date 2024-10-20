package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Comentario;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ComentariosRepository")
public interface ComentariosRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByTarea(Tarea tarea);
    List<Comentario> findByUsuario(Usuario usuario);
    List<Comentario> findByFechaComentario(Date fechaComentario);
}
