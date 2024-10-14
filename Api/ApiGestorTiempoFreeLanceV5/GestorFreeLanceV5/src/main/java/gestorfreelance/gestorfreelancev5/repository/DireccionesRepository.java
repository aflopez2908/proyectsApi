package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Ciudades;
import gestorfreelance.gestorfreelancev5.model.Direcciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("DireccionesRepository")
public interface DireccionesRepository extends JpaRepository<Direcciones, Integer> {
    List<Direcciones> findByCalleContaining(String calle);
    List<Direcciones> findByCiudad(Ciudades ciudad);
}
