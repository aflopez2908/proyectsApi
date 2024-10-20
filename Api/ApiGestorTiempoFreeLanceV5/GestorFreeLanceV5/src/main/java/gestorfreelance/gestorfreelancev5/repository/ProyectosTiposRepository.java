package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoTipo;
import gestorfreelance.gestorfreelancev5.model.ProyectoTipoId;
import gestorfreelance.gestorfreelancev5.model.TipoProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ProyectosTiposRepository")
public interface ProyectosTiposRepository extends JpaRepository<ProyectoTipo, ProyectoTipoId> {
    List<ProyectoTipo> findByProyecto(Proyecto proyecto);
    List<ProyectoTipo> findByTipo(TipoProyecto tipo);
}
