package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.model.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PeticionRepository extends JpaRepository<Peticion, Long> {

    Peticion findByIdPeticion(Long peticionId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM peticion_estado WHERE peticion_id = :idPeticion; " +
            "DELETE FROM asignacion WHERE id_peticion = :idPeticion; " ,
            nativeQuery = true)
    void eliminarRelacionesPorPeticionId(@Param("idPeticion") Long idPeticion);




}
