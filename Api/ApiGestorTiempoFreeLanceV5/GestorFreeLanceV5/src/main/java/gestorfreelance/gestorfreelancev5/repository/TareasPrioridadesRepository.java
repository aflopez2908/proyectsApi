package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.PrioridadesTarea;
import gestorfreelance.gestorfreelancev5.model.Tareas;
import gestorfreelance.gestorfreelancev5.model.TareasPrioridades;
import gestorfreelance.gestorfreelancev5.model.TareasPrioridadesId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TareasPrioridadesRepository")
public interface TareasPrioridadesRepository extends JpaRepository<TareasPrioridades, TareasPrioridadesId> {
    List<TareasPrioridades> findByTarea(Tareas tarea);
    List<TareasPrioridades> findByPrioridad(PrioridadesTarea prioridad);
}
