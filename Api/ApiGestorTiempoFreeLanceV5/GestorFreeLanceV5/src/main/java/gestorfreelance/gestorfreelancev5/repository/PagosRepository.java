package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Facturacion;
import gestorfreelance.gestorfreelancev5.model.Pagos;
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
