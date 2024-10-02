/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Eventos;
import entity.Proyectos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("EventosRepository")
public interface EventosRepository extends JpaRepository<Eventos, Integer> {
    List<Eventos> findByProyecto(Proyectos proyecto);
    List<Eventos> findByNombreContaining(String nombre);
    List<Eventos> findByFechaEvento(Date fechaEvento);
}
