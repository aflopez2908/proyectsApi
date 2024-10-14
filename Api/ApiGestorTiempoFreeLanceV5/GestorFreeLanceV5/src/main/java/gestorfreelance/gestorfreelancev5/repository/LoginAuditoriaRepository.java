package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.LoginAuditoria;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("LoginAuditoriaRepository")
public interface LoginAuditoriaRepository extends JpaRepository<LoginAuditoria, Integer> {
    List<LoginAuditoria> findByUsuario(Usuarios usuario);
    List<LoginAuditoria> findByFechaLogin(Date fechaLogin);
    List<LoginAuditoria> findByExitoso(Boolean exitoso);
}
