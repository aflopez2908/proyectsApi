package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Facturacion;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("FacturacionRepository")
public interface FacturacionRepository extends JpaRepository<Facturacion, Integer> {
    List<Facturacion> findByProyecto(Proyecto proyecto);
    List<Facturacion> findByFechaEmision(Date fechaEmision);
    List<Facturacion> findByFechaVencimiento(Date fechaVencimiento);
    @Modifying
    @Transactional
    @Query("DELETE FROM Facturacion b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}
