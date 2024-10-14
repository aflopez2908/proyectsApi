package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Roles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RolesRepository")
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    List<Roles> findByNombreContaining(String nombre);
}
