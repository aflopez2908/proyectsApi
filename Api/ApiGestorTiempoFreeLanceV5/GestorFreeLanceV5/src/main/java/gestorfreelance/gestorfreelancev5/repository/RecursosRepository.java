package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Recurso;
import gestorfreelance.gestorfreelancev5.model.TipoRecurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RecursosRepository")
public interface RecursosRepository extends JpaRepository<Recurso, Integer> {
    List<Recurso> findByProyecto(Proyecto proyecto);
    List<Recurso> findByTipo(TipoRecurso tipo);
    List<Recurso> findByNombreContaining(String nombre);
}