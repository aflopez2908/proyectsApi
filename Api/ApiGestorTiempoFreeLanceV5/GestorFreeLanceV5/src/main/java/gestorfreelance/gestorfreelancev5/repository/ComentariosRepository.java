package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Comentarios;
import gestorfreelance.gestorfreelancev5.model.Tareas;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ComentariosRepository")
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {
    List<Comentarios> findByTarea(Tareas tarea);
    List<Comentarios> findByUsuario(Usuarios usuario);
    List<Comentarios> findByFechaComentario(Date fechaComentario);
}
