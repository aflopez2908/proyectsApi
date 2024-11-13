package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.PeticionEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PeticionEstadoRepository extends JpaRepository<PeticionEstado, Integer> {


    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PeticionEstado p WHERE p.peticion.idPeticion = :peticionId AND p.peticionEstadoId = 5")
    boolean existsByPeticionIdAndPeticionEstadoIdEqualsTwo(@Param("peticionId") Long peticionId);



    @Query("SELECT pe FROM PeticionEstado pe WHERE pe.peticion.idPeticion = :idPeticion")
    List<PeticionEstado> findHistorialByPeticionId(@Param("idPeticion") Long idPeticion);

    @Modifying
    @Transactional
    @Query("UPDATE PeticionEstado pe SET pe.vigencia = 0 WHERE pe.peticion.idPeticion = :id")
    void actualizarVigenciaPeticion(Long id);

    @Query("SELECT p FROM PeticionEstado p WHERE p.vigencia = 1 AND p.peticion.idPeticion = :peticionId")
    PeticionEstado findByPeticionId(@Param("peticionId") Long peticionId);



}
