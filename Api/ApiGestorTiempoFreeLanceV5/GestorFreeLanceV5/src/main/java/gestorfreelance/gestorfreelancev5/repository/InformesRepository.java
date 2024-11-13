package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Informe;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("InformesRepository")
public interface InformesRepository extends JpaRepository<Informe, Integer> {
    List<Informe> findByProyecto(Proyecto proyecto);
    List<Informe> findByNombreContaining(String nombre);
    List<Informe> findByFechaCreacion(Date fechaCreacion);
    @Modifying
    @Transactional
    @Query("DELETE FROM Informe b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);
}
