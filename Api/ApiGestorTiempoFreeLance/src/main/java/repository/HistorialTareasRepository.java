/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.HistorialTareas;
import entity.Tareas;
import entity.Usuarios;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("HistorialTareasRepository")
public interface HistorialTareasRepository extends JpaRepository<HistorialTareas, UUID> {
    List<HistorialTareas> findByTarea(Tareas tarea);
    List<HistorialTareas> findByUsuario(Usuarios usuario);
    List<HistorialTareas> findByFechaCambio(Date fechaCambio);
}
