package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.model.Recursos;
import gestorfreelance.gestorfreelancev5.model.TiposRecurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RecursosRepository")
public interface RecursosRepository extends JpaRepository<Recursos, Integer> {
    List<Recursos> findByProyecto(Proyectos proyecto);
    List<Recursos> findByTipo(TiposRecurso tipo);
    List<Recursos> findByNombreContaining(String nombre);
}