package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("UsuariosRepository")
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombre(String nombre);
    //Usuario findByNombre(String nombre);
    List<Usuario> findByNombreContaining(String nombre);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByRol(Rol rol);
    List<Usuario> findByNombreAndRol(String nombre, Rol rol);
    Usuario findByEmailAndRol(String email, Rol rol);
    Usuario findByUsuarioId(Integer usuarioId);
    Usuario findByNombreAndContraseña(String nombre, String contrasena);

    @Query("SELECT u FROM Usuario u " +
            "INNER JOIN Cliente c ON u.nombre = c.nombre " +
            "INNER JOIN Proyecto p ON c.clienteId = p.cliente.clienteId " +
            "WHERE p.proyectoId = :proyectoId")
    Usuario findMatchClient(@Param("proyectoId") Long proyectoId);
}