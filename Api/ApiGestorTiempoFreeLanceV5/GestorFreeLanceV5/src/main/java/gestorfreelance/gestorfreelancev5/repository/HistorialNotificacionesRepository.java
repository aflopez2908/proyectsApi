package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.HistorialNotificaciones;
import gestorfreelance.gestorfreelancev5.model.Notificaciones;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HistorialNotificacionesRepository")
public interface HistorialNotificacionesRepository extends JpaRepository<HistorialNotificaciones, Integer> {
    List<HistorialNotificaciones> findByNotificacion(Notificaciones notificacion);
    List<HistorialNotificaciones> findByUsuario(Usuarios usuario);
    List<HistorialNotificaciones> findByFechaEnvio(Date fechaEnvio);
}
