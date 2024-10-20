package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Documento;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("DocumentosRepository")
public interface DocumentosRepository extends JpaRepository<Documento, Integer> {
    List<Documento> findByTarea(Tarea tarea);
    List<Documento> findByFechaSubida(Date fechaSubida);
    List<Documento> findBySubidoPor(Usuario subidoPor);
}
