/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Documentos;
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
@Repository("DocumentosRepository")
public interface DocumentosRepository extends JpaRepository<Documentos, Integer> {
    List<Documentos> findByTarea(Tareas tarea);
    List<Documentos> findByFechaSubida(Date fechaSubida);
    List<Documentos> findBySubidoPor(Usuarios subidoPor);
}
