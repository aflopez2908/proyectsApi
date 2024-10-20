package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EvaluacionDesempeno;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EvaluacionesDesempenoRepository")
public interface EvaluacionesDesempenoRepository extends JpaRepository<EvaluacionDesempeno, Integer> {
    List<EvaluacionDesempeno> findByUsuario(Usuario usuario);
    List<EvaluacionDesempeno> findByFechaEvaluacion(Date fechaEvaluacion);
}