package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Usuarios;
import gestorfreelance.gestorfreelancev5.model.Roles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("UsuariosRepository")
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    List<Usuarios> findByNombreContaining(String nombre);
    Usuarios findByEmail(String email);
    List<Usuarios> findByRol(Roles rol);
    List<Usuarios> findByNombreAndRol(String nombre, Roles rol);   //  con el mismo nombre y rol (opcional, si también necesitas validar el nombre)
    Usuarios findByEmailAndRol(String email, Roles rol);
}