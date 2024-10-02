/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.HistorialNotificaciones;
import entity.Notificaciones;
import entity.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("HistorialNotificacionesRepository")
public interface HistorialNotificacionesRepository extends JpaRepository<HistorialNotificaciones, Integer> {
    List<HistorialNotificaciones> findByNotificacion(Notificaciones notificacion);
    List<HistorialNotificaciones> findByUsuario(Usuarios usuario);
    List<HistorialNotificaciones> findByFechaEnvio(Date fechaEnvio);
}
