package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.PrioridadTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("PrioridadesTareaRepository")
public interface PrioridadesTareaRepository extends JpaRepository<PrioridadTarea, Integer> {
    PrioridadTarea findByPrioridadId(Integer prioridadId);
}
