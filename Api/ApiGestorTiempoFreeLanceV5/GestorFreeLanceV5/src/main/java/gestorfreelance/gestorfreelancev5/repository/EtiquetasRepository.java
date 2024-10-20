package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Etiqueta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EtiquetasRepository")
public interface EtiquetasRepository extends JpaRepository<Etiqueta, Integer> {
    List<Etiqueta> findByNombreContaining(String nombre);
}
