package gestorfreelance.gestorfreelancev5.repository;


import java.sql.Date;
import java.util.List;

import gestorfreelance.gestorfreelancev5.model.Actividad;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("ActividadesRepository")
public interface ActividadesRepository extends JpaRepository<Actividad, Integer>{
    List<Actividad> findByNombre(String nombre);
    List<Actividad> findByProyecto(Proyecto proyecto);
    List<Actividad> findByFechaActividad(Date fechaActividad);
    @Modifying
    @Transactional
    @Query("DELETE FROM Actividad b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}
