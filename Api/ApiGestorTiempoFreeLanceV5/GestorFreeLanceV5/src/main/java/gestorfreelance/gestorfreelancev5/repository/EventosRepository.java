package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Evento;
import gestorfreelance.gestorfreelancev5.model.Proyecto;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("EventosRepository")
public interface EventosRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByProyecto(Proyecto proyecto);
    List<Evento> findByNombreContaining(String nombre);
    List<Evento> findByFechaEvento(Date fechaEvento);
    @Modifying
    @Transactional
    @Query("DELETE FROM Evento b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}
