package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Facturacion;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("FacturacionRepository")
public interface FacturacionRepository extends JpaRepository<Facturacion, Integer> {
    List<Facturacion> findByProyecto(Proyectos proyecto);
    List<Facturacion> findByFechaEmision(Date fechaEmision);
    List<Facturacion> findByFechaVencimiento(Date fechaVencimiento);
}
