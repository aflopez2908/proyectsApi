/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Actividades;
import entity.Proyectos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ActividadesRepository")
public interface ActividadesRepository extends JpaRepository<Actividades, Integer>{
    List<Actividades> findByNombre(String nombre);
    List<Actividades> findByProyecto(Proyectos proyecto);
    List<Actividades> findByFechaActividad(Date fechaActividad);   
}
