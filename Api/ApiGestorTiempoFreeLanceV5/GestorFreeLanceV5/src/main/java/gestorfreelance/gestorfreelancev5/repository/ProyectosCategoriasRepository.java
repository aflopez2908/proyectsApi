package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.CategoriaProyecto;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoCategoria;
import gestorfreelance.gestorfreelancev5.model.ProyectoCategoriaId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("ProyectosCategoriasRepository")
public interface ProyectosCategoriasRepository extends JpaRepository<ProyectoCategoria, ProyectoCategoriaId> {
    List<ProyectoCategoria> findByProyecto(Proyecto proyecto);
    List<ProyectoCategoria> findByCategoria(CategoriaProyecto categoria);
    @Modifying
    @Transactional
    @Query("DELETE FROM ProyectoCategoria b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}