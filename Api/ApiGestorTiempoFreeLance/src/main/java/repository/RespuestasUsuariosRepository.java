/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.RespuestasEncuesta;
import entity.RespuestasUsuarios;
import entity.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("RespuestasUsuariosRepository")
public interface RespuestasUsuariosRepository extends JpaRepository<RespuestasUsuarios, Integer> {
    List<RespuestasUsuarios> findByRespuesta(RespuestasEncuesta respuesta);
    List<RespuestasUsuarios> findByUsuario(Usuarios usuario);
}
