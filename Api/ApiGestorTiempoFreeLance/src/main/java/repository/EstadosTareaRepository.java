/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.EstadosTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("EstadosTareaRepository")
public interface EstadosTareaRepository extends JpaRepository<EstadosTarea, Integer> {
    EstadosTarea findByNombre(String nombre);
}
