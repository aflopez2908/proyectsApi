/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.LoginAuditoria;
import entity.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("LoginAuditoriaRepository")
public interface LoginAuditoriaRepository extends JpaRepository<LoginAuditoria, Integer> {
    List<LoginAuditoria> findByUsuario(Usuarios usuario);
    List<LoginAuditoria> findByFechaLogin(Date fechaLogin);
    List<LoginAuditoria> findByExitoso(Boolean exitoso);
}
