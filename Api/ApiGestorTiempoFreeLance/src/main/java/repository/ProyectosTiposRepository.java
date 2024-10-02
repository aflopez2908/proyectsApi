/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Proyectos;
import entity.ProyectosTipos;
import entity.ProyectosTiposId;
import entity.TiposProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ProyectosTiposRepository")
public interface ProyectosTiposRepository extends JpaRepository<ProyectosTipos, ProyectosTiposId> {
    List<ProyectosTipos> findByProyecto(Proyectos proyecto);
    List<ProyectosTipos> findByTipo(TiposProyecto tipo);
}
