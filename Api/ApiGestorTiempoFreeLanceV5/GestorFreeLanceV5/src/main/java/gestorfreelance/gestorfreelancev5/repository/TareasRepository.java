package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EstadosTarea;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.model.Tareas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TareasRepository")
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
    List<Tareas> findByProyecto(Proyectos proyecto);
    List<Tareas> findByEstado(EstadosTarea estado);
    List<Tareas> findByNombreContaining(String nombre);
}