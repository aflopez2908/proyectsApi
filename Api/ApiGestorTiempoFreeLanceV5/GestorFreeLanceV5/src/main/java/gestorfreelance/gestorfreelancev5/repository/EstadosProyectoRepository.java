package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EstadosProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EstadosProyectoRepository")
public interface EstadosProyectoRepository extends JpaRepository<EstadosProyecto, Integer> {
    EstadosProyecto findByNombre(String nombre);
}
