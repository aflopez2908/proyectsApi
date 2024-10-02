/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Ciudades;
import entity.Paises;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("CiudadesRepository")
public interface CiudadesRepository extends JpaRepository<Ciudades, Integer> {
    List<Ciudades> findByNombre(String nombre);
    List<Ciudades> findByPais(Paises pais);
}
