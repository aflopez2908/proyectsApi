package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Producto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ProductosRepository")
public interface ProductosRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByCostoGreaterThan(Double costo);
}
