package gestorfreelance.gestorfreelancev5.repository;


import gestorfreelance.gestorfreelancev5.model.Producto;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoProducto;
import gestorfreelance.gestorfreelancev5.model.ProyectoProductoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("ProyectosProductosRepository")
public interface ProyectosProductosRepository extends JpaRepository<ProyectoProducto, ProyectoProductoId> {
    List<ProyectoProducto> findByProyecto(Proyecto proyecto);
    List<ProyectoProducto> findByProducto(Producto producto);
    @Modifying
    @Transactional
    @Query("DELETE FROM ProyectoProducto b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}
