package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.TiposProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TiposProyectoRepository")
public interface TiposProyectoRepository extends JpaRepository<TiposProyecto, Integer> {
    List<TiposProyecto> findByNombreContaining(String nombre);
}