package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Productos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ProductosRepository")
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    List<Productos> findByNombreContaining(String nombre);
    List<Productos> findByCostoGreaterThan(Double costo);
}
