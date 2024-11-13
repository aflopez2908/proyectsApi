package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Recurso;
import gestorfreelance.gestorfreelancev5.model.TipoRecurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("RecursosRepository")
public interface RecursosRepository extends JpaRepository<Recurso, Integer> {
    List<Recurso> findByProyecto(Proyecto proyecto);
    List<Recurso> findByTipo(TipoRecurso tipo);
    List<Recurso> findByNombreContaining(String nombre);
    @Modifying
    @Transactional
    @Query("DELETE FROM Recurso b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}