/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Productos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ProductosRepository")
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    List<Productos> findByNombreContaining(String nombre);
    List<Productos> findByCostoGreaterThan(Double costo);
}
