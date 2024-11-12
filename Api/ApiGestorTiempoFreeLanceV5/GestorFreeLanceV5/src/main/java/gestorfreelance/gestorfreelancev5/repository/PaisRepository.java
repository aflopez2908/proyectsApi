package gestorfreelance.gestorfreelancev5.repository;


import gestorfreelance.gestorfreelancev5.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Integer> {
    Optional<Pais> findByNombre(String nombre);

}