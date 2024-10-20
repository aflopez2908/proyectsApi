package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.HistorialTarea;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HistorialTareasRepository")
public interface HistorialTareasRepository extends JpaRepository<HistorialTarea, UUID> {
    List<HistorialTarea> findByTarea(Tarea tarea);
    List<HistorialTarea> findByUsuario(Usuario usuario);
    List<HistorialTarea> findByFechaCambio(Date fechaCambio);
}
