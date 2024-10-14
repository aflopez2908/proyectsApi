package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Encuestas;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("EncuestasRepository")
public interface EncuestasRepository extends JpaRepository<Encuestas, Integer> {
    List<Encuestas> findByNombreContaining(String nombre);
    List<Encuestas> findByFechaCreacion(Date fechaCreacion);
}
