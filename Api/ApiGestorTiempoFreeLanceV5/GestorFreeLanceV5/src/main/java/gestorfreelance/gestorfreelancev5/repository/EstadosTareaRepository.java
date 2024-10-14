package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EstadosTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EstadosTareaRepository")
public interface EstadosTareaRepository extends JpaRepository<EstadosTarea, Integer> {
    EstadosTarea findByNombre(String nombre);
}
