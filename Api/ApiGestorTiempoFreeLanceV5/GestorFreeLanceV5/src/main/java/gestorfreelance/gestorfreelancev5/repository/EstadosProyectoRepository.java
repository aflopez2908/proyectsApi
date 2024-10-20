package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Clientes;
import gestorfreelance.gestorfreelancev5.model.EstadosProyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("EstadosProyectoRepository")
public interface EstadosProyectoRepository extends JpaRepository<EstadosProyecto, Integer> {
    EstadosProyecto findByNombre(String nombre);
    EstadosProyecto findById(int id);

    @Query("SELECT p.proyectoId, p.nombre,p.descripcion, p.cliente, p.fechaInicio, p.fechaFin, e.proyectoEstadoId, e.fechaCambio, e.comentario, e.vigencia FROM Proyectos p INNER JOIN ProyectoEstado e ON p.proyectoId = :id")
    EstadosProyecto customFind(@Param("id") int id);

}
