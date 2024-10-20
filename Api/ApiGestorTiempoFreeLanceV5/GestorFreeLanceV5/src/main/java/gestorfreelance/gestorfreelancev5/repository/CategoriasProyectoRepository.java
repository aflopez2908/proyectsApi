package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.CategoriaProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("CategoriasProyectoRepository")
public interface CategoriasProyectoRepository extends JpaRepository<CategoriaProyecto, Integer> {
     List<CategoriaProyecto> findByNombreContaining(String nombre);
}
