package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.HistorialNotificacion;
import gestorfreelance.gestorfreelancev5.model.Notificacion;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HistorialNotificacionesRepository")
public interface HistorialNotificacionesRepository extends JpaRepository<HistorialNotificacion, Integer> {
    List<HistorialNotificacion> findByNotificacion(Notificacion notificacion);
    List<HistorialNotificacion> findByUsuario(Usuario usuario);
    List<HistorialNotificacion> findByFechaEnvio(Date fechaEnvio);
}
