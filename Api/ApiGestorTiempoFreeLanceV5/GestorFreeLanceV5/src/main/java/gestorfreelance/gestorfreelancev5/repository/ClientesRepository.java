package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.model.Direccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("ClientesRepository")
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNombreContaining(String nombre);
    Cliente findByEmail(String email);
    List<Cliente> findByDireccion(Direccion direccion);

    Cliente findByClienteId(Integer id);




    @Query("SELECT c FROM Cliente c JOIN Proyecto p WHERE p.proyectoId = :proyectoId")
    Cliente findClienteByProyectoId(@Param("proyectoId") Long proyectoId);

}
