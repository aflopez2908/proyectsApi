package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.ERol;
import gestorfreelance.gestorfreelancev5.model.Rol;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RolesRepository")
public interface RolesRepository extends JpaRepository<Rol, Long> {
    List<Rol> findByNombreContaining(String nombre);
    Optional<Rol> findByNombre(ERol nombre);
}
