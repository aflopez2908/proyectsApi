package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Ciudades;
import gestorfreelance.gestorfreelancev5.model.Paises;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("CiudadesRepository")
public interface CiudadesRepository extends JpaRepository<Ciudades, Integer> {
    List<Ciudades> findByNombre(String nombre);
    List<Ciudades> findByPais(Paises pais);
}
