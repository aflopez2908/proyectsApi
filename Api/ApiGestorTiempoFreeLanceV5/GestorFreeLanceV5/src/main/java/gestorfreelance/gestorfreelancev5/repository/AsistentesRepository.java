package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Asistente;
import gestorfreelance.gestorfreelancev5.model.Evento;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AsistentesRepository")
public interface AsistentesRepository extends JpaRepository<Asistente, Integer> {
    List<Asistente> findByEvento(Evento evento);
    List<Asistente> findByUsuario(Usuario usuario);
}
