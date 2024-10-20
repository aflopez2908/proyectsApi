package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Permiso;
import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.RolPermiso;
import gestorfreelance.gestorfreelancev5.model.RolPermisoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RolesPermisosRepository")
public interface RolesPermisosRepository extends JpaRepository<RolPermiso, RolPermisoId> {
    List<RolPermiso> findByRol(Rol rol);
    List<RolPermiso> findByPermiso(Permiso permiso);
}
