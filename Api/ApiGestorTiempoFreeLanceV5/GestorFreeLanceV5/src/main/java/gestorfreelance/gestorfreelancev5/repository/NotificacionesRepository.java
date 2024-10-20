package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Notificacion;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("NotificacionesRepository")
public interface NotificacionesRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUsuario(Usuario usuario);
    List<Notificacion> findByFechaNotificacion(Date fechaNotificacion);
    List<Notificacion> findByLeida(Boolean leida);
}