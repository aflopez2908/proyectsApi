package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Asistentes;
import gestorfreelance.gestorfreelancev5.model.Eventos;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AsistentesRepository")
public interface AsistentesRepository extends JpaRepository<Asistentes, Integer> {
    List<Asistentes> findByEvento(Eventos evento);
    List<Asistentes> findByUsuario(Usuarios usuario);    
}
