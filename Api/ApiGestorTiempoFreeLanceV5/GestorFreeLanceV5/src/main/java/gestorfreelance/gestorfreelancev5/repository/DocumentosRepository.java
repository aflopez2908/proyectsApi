package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Documentos;
import gestorfreelance.gestorfreelancev5.model.Tareas;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("DocumentosRepository")
public interface DocumentosRepository extends JpaRepository<Documentos, Integer> {
    List<Documentos> findByTarea(Tareas tarea);
    List<Documentos> findByFechaSubida(Date fechaSubida);
    List<Documentos> findBySubidoPor(Usuarios subidoPor);
}
