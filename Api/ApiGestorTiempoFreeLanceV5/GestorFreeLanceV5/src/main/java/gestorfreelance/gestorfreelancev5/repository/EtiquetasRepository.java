package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Etiquetas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EtiquetasRepository")
public interface EtiquetasRepository extends JpaRepository<Etiquetas, Integer> {
    List<Etiquetas> findByNombreContaining(String nombre);
}
