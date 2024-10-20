package gestorfreelance.gestorfreelancev5.repository;


import gestorfreelance.gestorfreelancev5.model.Producto;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoProducto;
import gestorfreelance.gestorfreelancev5.model.ProyectoProductoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ProyectosProductosRepository")
public interface ProyectosProductosRepository extends JpaRepository<ProyectoProducto, ProyectoProductoId> {
    List<ProyectoProducto> findByProyecto(Proyecto proyecto);
    List<ProyectoProducto> findByProducto(Producto producto);
}
