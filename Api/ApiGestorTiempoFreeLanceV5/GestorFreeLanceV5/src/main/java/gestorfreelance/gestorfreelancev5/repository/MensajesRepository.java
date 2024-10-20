package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Chat;
import gestorfreelance.gestorfreelancev5.model.Mensaje;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MensajesRepository")
public interface MensajesRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByChat(Chat chat);
    List<Mensaje> findByUsuario(Usuario usuario);
    List<Mensaje> findByFechaMensaje(Date fechaMensaje);
}
