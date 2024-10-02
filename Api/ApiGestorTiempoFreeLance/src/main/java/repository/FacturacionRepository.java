/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Facturacion;
import entity.Proyectos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("FacturacionRepository")
public interface FacturacionRepository extends JpaRepository<Facturacion, Integer> {
    List<Facturacion> findByProyecto(Proyectos proyecto);
    List<Facturacion> findByFechaEmision(Date fechaEmision);
    List<Facturacion> findByFechaVencimiento(Date fechaVencimiento);
}
