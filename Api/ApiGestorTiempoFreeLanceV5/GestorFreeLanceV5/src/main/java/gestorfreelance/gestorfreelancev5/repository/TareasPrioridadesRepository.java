package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.PrioridadTarea;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.model.TareaPrioridad;
import gestorfreelance.gestorfreelancev5.model.TareaPrioridadId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TareasPrioridadesRepository")
public interface TareasPrioridadesRepository extends JpaRepository<TareaPrioridad, TareaPrioridadId> {
    List<TareaPrioridad> findByTarea(Tarea tarea);
    List<TareaPrioridad> findByPrioridad(PrioridadTarea prioridad);
}
