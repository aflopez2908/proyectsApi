package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Informe;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("InformesRepository")
public interface InformesRepository extends JpaRepository<Informe, Integer> {
    List<Informe> findByProyecto(Proyecto proyecto);
    List<Informe> findByNombreContaining(String nombre);
    List<Informe> findByFechaCreacion(Date fechaCreacion);
}
