package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Evento;
import gestorfreelance.gestorfreelancev5.model.Proyecto;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("EventosRepository")
public interface EventosRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByProyecto(Proyecto proyecto);
    List<Evento> findByNombreContaining(String nombre);
    List<Evento> findByFechaEvento(Date fechaEvento);
}
