package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Permisos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PermisosRepository")
public interface PermisosRepository extends JpaRepository<Permisos, Integer> {
    List<Permisos> findByNombreContaining(String nombre);
}