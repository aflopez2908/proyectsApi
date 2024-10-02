/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.PrioridadesTarea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PrioridadesTareaRepository")
public interface PrioridadesTareaRepository extends JpaRepository<PrioridadesTarea, Integer> {
    List<PrioridadesTarea> findByNombreContaining(String nombre);
}
