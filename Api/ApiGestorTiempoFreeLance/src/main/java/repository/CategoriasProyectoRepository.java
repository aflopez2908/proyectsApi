/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.CategoriasProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("CategoriasProyectoRepository")
public interface CategoriasProyectoRepository extends JpaRepository<CategoriasProyecto, Integer> {
     List<CategoriasProyecto> findByNombreContaining(String nombre);   
}
