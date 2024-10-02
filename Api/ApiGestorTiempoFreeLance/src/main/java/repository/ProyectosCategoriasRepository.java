/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.CategoriasProyecto;
import entity.Proyectos;
import entity.ProyectosCategorias;
import entity.ProyectosCategoriasId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ProyectosCategoriasRepository")
public interface ProyectosCategoriasRepository extends JpaRepository<ProyectosCategorias, ProyectosCategoriasId> {
    List<ProyectosCategorias> findByProyecto(Proyectos proyecto);
    List<ProyectosCategorias> findByCategoria(CategoriasProyecto categoria);
}