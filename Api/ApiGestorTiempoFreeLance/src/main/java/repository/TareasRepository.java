/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.EstadosTarea;
import entity.Proyectos;
import entity.Tareas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("TareasRepository")
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
    List<Tareas> findByProyecto(Proyectos proyecto);
    List<Tareas> findByEstado(EstadosTarea estado);
    List<Tareas> findByNombreContaining(String nombre);
}