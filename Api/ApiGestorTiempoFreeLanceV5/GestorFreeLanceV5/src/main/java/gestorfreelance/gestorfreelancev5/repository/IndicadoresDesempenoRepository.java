package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.IndicadorDesempeno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("IndicadoresDesempenoRepository")
public interface IndicadoresDesempenoRepository extends JpaRepository<IndicadorDesempeno, Integer> {
    List<IndicadorDesempeno> findByNombreContaining(String nombre);
}
