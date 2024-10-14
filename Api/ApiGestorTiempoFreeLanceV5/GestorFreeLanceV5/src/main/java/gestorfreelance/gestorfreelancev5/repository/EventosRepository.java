package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Eventos;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("EventosRepository")
public interface EventosRepository extends JpaRepository<Eventos, Integer> {
    List<Eventos> findByProyecto(Proyectos proyecto);
    List<Eventos> findByNombreContaining(String nombre);
    List<Eventos> findByFechaEvento(Date fechaEvento);
}
