package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Ciudad;
import gestorfreelance.gestorfreelancev5.model.Direccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("DireccionesRepository")
public interface DireccionesRepository extends JpaRepository<Direccion, Integer> {
    List<Direccion> findByCalleContaining(String calle);
    List<Direccion> findByCiudad(Ciudad ciudad);
}
