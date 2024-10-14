package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.CategoriasProyecto;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.model.ProyectosCategorias;
import gestorfreelance.gestorfreelancev5.model.ProyectosCategoriasId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ProyectosCategoriasRepository")
public interface ProyectosCategoriasRepository extends JpaRepository<ProyectosCategorias, ProyectosCategoriasId> {
    List<ProyectosCategorias> findByProyecto(Proyectos proyecto);
    List<ProyectosCategorias> findByCategoria(CategoriasProyecto categoria);
}