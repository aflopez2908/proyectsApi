package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Ciudad;
import gestorfreelance.gestorfreelancev5.model.Pais;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("CiudadesRepository")
public interface CiudadesRepository extends JpaRepository<Ciudad, Integer> {
    List<Ciudad> findByNombre(String nombre);
    List<Ciudad> findByPais(Pais pais);
   // Optional<Ciudad> findByNombreAndPaisId(String nombre, Integer paisId);
}
