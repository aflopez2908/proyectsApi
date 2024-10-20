package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.CategoriaProyecto;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoCategoria;
import gestorfreelance.gestorfreelancev5.model.ProyectoCategoriaId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ProyectosCategoriasRepository")
public interface ProyectosCategoriasRepository extends JpaRepository<ProyectoCategoria, ProyectoCategoriaId> {
    List<ProyectoCategoria> findByProyecto(Proyecto proyecto);
    List<ProyectoCategoria> findByCategoria(CategoriaProyecto categoria);
}