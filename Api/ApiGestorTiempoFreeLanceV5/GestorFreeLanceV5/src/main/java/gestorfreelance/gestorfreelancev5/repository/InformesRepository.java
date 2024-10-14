package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Informes;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("InformesRepository")
public interface InformesRepository extends JpaRepository<Informes, Integer> {
    List<Informes> findByProyecto(Proyectos proyecto);
    List<Informes> findByNombreContaining(String nombre);
    List<Informes> findByFechaCreacion(Date fechaCreacion);
}
