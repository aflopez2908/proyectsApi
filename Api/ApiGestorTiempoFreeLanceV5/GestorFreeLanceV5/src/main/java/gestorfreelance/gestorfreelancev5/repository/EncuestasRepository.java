package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Encuesta;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("EncuestasRepository")
public interface EncuestasRepository extends JpaRepository<Encuesta, Integer> {
    List<Encuesta> findByNombreContaining(String nombre);
    List<Encuesta> findByFechaCreacion(Date fechaCreacion);
}
