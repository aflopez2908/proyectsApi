/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Facturacion;
import entity.Pagos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PagosRepository")
public interface PagosRepository extends JpaRepository<Pagos, Integer> {
    List<Pagos> findByFactura(Facturacion factura);
    List<Pagos> findByFechaPago(Date fechaPago);
    List<Pagos> findByMetodoPago(String metodoPago);
}
