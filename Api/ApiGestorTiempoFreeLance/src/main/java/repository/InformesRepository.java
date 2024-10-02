/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Informes;
import entity.Proyectos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("InformesRepository")
public interface InformesRepository extends JpaRepository<Informes, Integer> {
    List<Informes> findByProyecto(Proyectos proyecto);
    List<Informes> findByNombreContaining(String nombre);
    List<Informes> findByFechaCreacion(Date fechaCreacion);
}
