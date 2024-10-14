package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Permisos;
import gestorfreelance.gestorfreelancev5.model.Roles;
import gestorfreelance.gestorfreelancev5.model.RolesPermisos;
import gestorfreelance.gestorfreelancev5.model.RolesPermisosId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RolesPermisosRepository")
public interface RolesPermisosRepository extends JpaRepository<RolesPermisos, RolesPermisosId> {
    List<RolesPermisos> findByRol(Roles rol);
    List<RolesPermisos> findByPermiso(Permisos permiso);
}
