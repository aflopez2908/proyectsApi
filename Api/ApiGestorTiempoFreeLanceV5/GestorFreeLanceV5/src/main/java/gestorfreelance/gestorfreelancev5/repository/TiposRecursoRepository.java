package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.TiposRecurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("TiposRecursoRepository")
public interface TiposRecursoRepository extends JpaRepository<TiposRecurso, Integer> {
    List<TiposRecurso> findByNombreContaining(String nombre);
}
