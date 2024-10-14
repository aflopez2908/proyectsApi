package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.PrioridadesTarea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PrioridadesTareaRepository")
public interface PrioridadesTareaRepository extends JpaRepository<PrioridadesTarea, Integer> {
    List<PrioridadesTarea> findByNombreContaining(String nombre);
}
