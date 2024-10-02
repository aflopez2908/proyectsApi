/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import entity.Clientes;
import entity.EstadosProyecto;
import entity.Productos;
import entity.Proyectos;
import entity.ProyectosProductos;
import entity.ProyectosProductosId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ProyectosProductosRepository")
public interface ProyectosProductosRepository extends JpaRepository<ProyectosProductos, ProyectosProductosId> {
    List<ProyectosProductos> findByProyecto(Proyectos proyecto);
    List<ProyectosProductos> findByProducto(Productos producto);
}
