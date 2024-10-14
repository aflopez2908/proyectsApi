package gestorfreelance.gestorfreelancev5.repository;


import gestorfreelance.gestorfreelancev5.model.Clientes;
import gestorfreelance.gestorfreelancev5.model.EstadosProyecto;
import gestorfreelance.gestorfreelancev5.model.Productos;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.model.ProyectosProductos;
import gestorfreelance.gestorfreelancev5.model.ProyectosProductosId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ProyectosProductosRepository")
public interface ProyectosProductosRepository extends JpaRepository<ProyectosProductos, ProyectosProductosId> {
    List<ProyectosProductos> findByProyecto(Proyectos proyecto);
    List<ProyectosProductos> findByProducto(Productos producto);
}
