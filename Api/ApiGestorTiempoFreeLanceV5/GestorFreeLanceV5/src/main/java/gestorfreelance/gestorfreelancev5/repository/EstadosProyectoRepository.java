package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EstadoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("EstadosProyectoRepository")
public interface EstadosProyectoRepository extends JpaRepository<EstadoProyecto, Integer> {
    EstadoProyecto findByNombre(String nombre);
    EstadoProyecto findById(int id);


}
