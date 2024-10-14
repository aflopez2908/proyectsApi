package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Notificaciones;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("NotificacionesRepository")
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Integer> {
    List<Notificaciones> findByUsuario(Usuarios usuario);
    List<Notificaciones> findByFechaNotificacion(Date fechaNotificacion);
    List<Notificaciones> findByLeida(Boolean leida);
}