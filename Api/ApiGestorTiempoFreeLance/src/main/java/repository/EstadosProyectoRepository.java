/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.EstadosProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("EstadosProyectoRepository")
public interface EstadosProyectoRepository extends JpaRepository<EstadosProyecto, Integer> {
    EstadosProyecto findByNombre(String nombre);
}
