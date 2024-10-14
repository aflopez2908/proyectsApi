package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.EvaluacionesDesempeno;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EvaluacionesDesempenoRepository")
public interface EvaluacionesDesempenoRepository extends JpaRepository<EvaluacionesDesempeno, Integer> {
    List<EvaluacionesDesempeno> findByUsuario(Usuarios usuario);
    List<EvaluacionesDesempeno> findByFechaEvaluacion(Date fechaEvaluacion);
}