package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("UsuariosRepository")
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByNombreContaining(String nombre);
    Usuario findByEmail(String email);
    List<Usuario> findByRol(Rol rol);
    List<Usuario> findByNombreAndRol(String nombre, Rol rol);   //  con el mismo nombre y rol (opcional, si también necesitas validar el nombre)
    Usuario findByEmailAndRol(String email, Rol rol);
}