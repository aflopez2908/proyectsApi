package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Chats;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ChatsRepository")
public interface ChatsRepository extends JpaRepository<Chats, Integer> {
    List<Chats> findByProyecto(Proyectos proyecto);
    List<Chats> findByNombreContaining(String nombre);
}
