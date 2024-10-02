/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Ciudades;
import entity.Direcciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("DireccionesRepository")
public interface DireccionesRepository extends JpaRepository<Direcciones, Integer> {
    List<Direcciones> findByCalleContaining(String calle);
    List<Direcciones> findByCiudad(Ciudades ciudad);
}
