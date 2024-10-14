package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Chats;
import gestorfreelance.gestorfreelancev5.model.Mensajes;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MensajesRepository")
public interface MensajesRepository extends JpaRepository<Mensajes, Integer> {
    List<Mensajes> findByChat(Chats chat);
    List<Mensajes> findByUsuario(Usuarios usuario);
    List<Mensajes> findByFechaMensaje(Date fechaMensaje);
}
