/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.TiposProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("TiposProyectoRepository")
public interface TiposProyectoRepository extends JpaRepository<TiposProyecto, Integer> {
    List<TiposProyecto> findByNombreContaining(String nombre);
}