package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.HistorialTareas;
import gestorfreelance.gestorfreelancev5.model.Tareas;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HistorialTareasRepository")
public interface HistorialTareasRepository extends JpaRepository<HistorialTareas, UUID> {
    List<HistorialTareas> findByTarea(Tareas tarea);
    List<HistorialTareas> findByUsuario(Usuarios usuario);
    List<HistorialTareas> findByFechaCambio(Date fechaCambio);
}
