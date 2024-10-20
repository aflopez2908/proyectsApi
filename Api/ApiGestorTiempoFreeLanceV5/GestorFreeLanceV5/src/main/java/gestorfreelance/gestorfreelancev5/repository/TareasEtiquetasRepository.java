package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Etiqueta;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.model.TareaEtiqueta;
import gestorfreelance.gestorfreelancev5.model.TareaEtiquetaId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TareasEtiquetasRepository")
public interface TareasEtiquetasRepository extends JpaRepository<TareaEtiqueta, TareaEtiquetaId> {
    List<TareaEtiqueta> findByTarea(Tarea tarea);
    List<TareaEtiqueta> findByEtiqueta(Etiqueta etiqueta);
}
