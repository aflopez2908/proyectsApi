package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Chat;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("ChatsRepository")
public interface ChatsRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findByProyecto(Proyecto proyecto);
    List<Chat> findByNombreContaining(String nombre);
    @Modifying
    @Transactional
    @Query("DELETE FROM Chat b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}
