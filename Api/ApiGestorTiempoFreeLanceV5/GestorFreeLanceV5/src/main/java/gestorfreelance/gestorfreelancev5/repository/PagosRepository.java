package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Facturacion;
import gestorfreelance.gestorfreelancev5.model.Pago;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PagosRepository")
public interface PagosRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByFactura(Facturacion factura);
    List<Pago> findByFechaPago(Date fechaPago);
    List<Pago> findByMetodoPago(String metodoPago);
}
