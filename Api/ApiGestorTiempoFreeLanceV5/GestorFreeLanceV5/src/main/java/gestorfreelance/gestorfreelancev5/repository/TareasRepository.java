package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EstadoTarea;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Tarea;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TareasRepository")
public interface TareasRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByProyecto(Proyecto proyecto);
    List<Tarea> findByNombreContaining(String nombre);
}