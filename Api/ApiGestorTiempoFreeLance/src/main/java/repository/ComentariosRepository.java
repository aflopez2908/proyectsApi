/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Comentarios;
import entity.Tareas;
import entity.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ComentariosRepository")
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {
    List<Comentarios> findByTarea(Tareas tarea);
    List<Comentarios> findByUsuario(Usuarios usuario);
    List<Comentarios> findByFechaComentario(Date fechaComentario);
}
