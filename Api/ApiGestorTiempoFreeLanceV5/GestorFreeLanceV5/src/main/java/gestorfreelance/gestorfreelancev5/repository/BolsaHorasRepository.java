package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("BolsaHorasRepository")
public interface BolsaHorasRepository  extends JpaRepository<BolsaHora, Integer>{
    List<BolsaHora> findByProyecto(Proyecto proyecto);
    List<BolsaHora> findByHorasRestantesGreaterThan(Integer horas);
    @Modifying
    @Transactional
    @Query("DELETE FROM BolsaHora b WHERE b.proyecto.proyectoId = :proyectoId")
    void deleteByProyectoId(@Param("proyectoId") Long proyectoId);

}
