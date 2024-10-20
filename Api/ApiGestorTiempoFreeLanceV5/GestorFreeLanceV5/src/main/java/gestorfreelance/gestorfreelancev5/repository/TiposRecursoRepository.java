package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.TipoRecurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TiposRecursoRepository")
public interface TiposRecursoRepository extends JpaRepository<TipoRecurso, Integer> {
    List<TipoRecurso> findByNombreContaining(String nombre);
}
