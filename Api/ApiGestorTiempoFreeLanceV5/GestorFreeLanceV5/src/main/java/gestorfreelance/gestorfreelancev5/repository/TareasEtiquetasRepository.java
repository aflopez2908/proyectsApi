package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Etiquetas;
import gestorfreelance.gestorfreelancev5.model.Tareas;
import gestorfreelance.gestorfreelancev5.model.TareasEtiquetas;
import gestorfreelance.gestorfreelancev5.model.TareasEtiquetasId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TareasEtiquetasRepository")
public interface TareasEtiquetasRepository extends JpaRepository<TareasEtiquetas, TareasEtiquetasId> {
    List<TareasEtiquetas> findByTarea(Tareas tarea);
    List<TareasEtiquetas> findByEtiqueta(Etiquetas etiqueta);
}
