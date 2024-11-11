package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.IntentoLogin;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntentoLoginRepository extends JpaRepository<IntentoLogin, Long> {
    Optional<IntentoLogin> findByUsuarioAndVigenteTrue(Usuario usuario);
}