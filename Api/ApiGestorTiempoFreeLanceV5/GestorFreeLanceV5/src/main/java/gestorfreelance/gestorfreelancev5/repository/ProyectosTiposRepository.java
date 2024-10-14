package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.model.ProyectosTipos;
import gestorfreelance.gestorfreelancev5.model.ProyectosTiposId;
import gestorfreelance.gestorfreelancev5.model.TiposProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ProyectosTiposRepository")
public interface ProyectosTiposRepository extends JpaRepository<ProyectosTipos, ProyectosTiposId> {
    List<ProyectosTipos> findByProyecto(Proyectos proyecto);
    List<ProyectosTipos> findByTipo(TiposProyecto tipo);
}
