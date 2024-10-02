/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Proyectos;
import entity.Recursos;
import entity.TiposRecurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("RecursosRepository")
public interface RecursosRepository extends JpaRepository<Recursos, Integer> {
    List<Recursos> findByProyecto(Proyectos proyecto);
    List<Recursos> findByTipo(TiposRecurso tipo);
    List<Recursos> findByNombreContaining(String nombre);
}