package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BolsaHorasRepository")
public interface BolsaHorasRepository  extends JpaRepository<BolsaHora, Integer>{
    List<BolsaHora> findByUsuario(Usuario usuario);
    List<BolsaHora> findByProyecto(Proyecto proyecto);
    List<BolsaHora> findByHorasRestantesGreaterThan(Integer horas);
}
