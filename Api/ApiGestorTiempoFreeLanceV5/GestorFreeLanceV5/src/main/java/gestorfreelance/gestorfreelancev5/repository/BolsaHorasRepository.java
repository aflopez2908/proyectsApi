package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.BolsaHoras;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BolsaHorasRepository")
public interface BolsaHorasRepository  extends JpaRepository<BolsaHoras, Integer>{
    List<BolsaHoras> findByUsuario(Usuarios usuario);
    List<BolsaHoras> findByProyecto(Proyectos proyecto);
    List<BolsaHoras> findByHorasRestantesGreaterThan(Integer horas);  
}
