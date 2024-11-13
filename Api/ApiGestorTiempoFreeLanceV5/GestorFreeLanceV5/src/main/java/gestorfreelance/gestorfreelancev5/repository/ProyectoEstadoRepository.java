package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository("ProyectoEstadoRepository")
public interface ProyectoEstadoRepository extends JpaRepository<ProyectoEstado,Long> {

    //busqueda por id para saber en que estado se encuentra
    @Query("SELECT p FROM ProyectoEstado p WHERE p.proyectoEstadoId = :proyectoId")
    ProyectoEstado findByProyectoId(@Param("proyectoId") Long proyectoId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProyectoEstado p WHERE p.proyecto.proyectoId = :proyectoId AND p.estado.estadoId = 4")
    boolean existsByProyectoIdAndProyectoEstadoIdEqualsTwo(@Param("proyectoId") Long proyectoId);




    //actualizacion a 0 de todas las vigencias
    @Modifying
    @Transactional
    @Query("UPDATE ProyectoEstado SET vigencia = 0 WHERE proyecto.proyectoId = :proyectoId")
    void actualizarVigencia(@Param("proyectoId") Long proyectoId);

    //Optional<ProyectoEstado> findByProyectoIdAndVigencia(Long proyectoId, int vigencia);
    Optional<ProyectoEstado> findByProyecto_ProyectoIdAndVigencia(int proyectoId, int vigencia);

}
