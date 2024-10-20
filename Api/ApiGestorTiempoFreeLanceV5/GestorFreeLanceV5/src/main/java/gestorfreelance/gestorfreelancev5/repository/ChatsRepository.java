package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Chat;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ChatsRepository")
public interface ChatsRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findByProyecto(Proyecto proyecto);
    List<Chat> findByNombreContaining(String nombre);
}
