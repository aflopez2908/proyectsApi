package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EstadosTareaRepository")
public interface EstadosTareaRepository extends JpaRepository<EstadoTarea, Integer> {
    EstadoTarea findByNombre(String nombre);
}
