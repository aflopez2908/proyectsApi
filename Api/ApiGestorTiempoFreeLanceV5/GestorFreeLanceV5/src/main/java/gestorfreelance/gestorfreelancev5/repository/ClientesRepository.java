package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Clientes;
import gestorfreelance.gestorfreelancev5.model.Direcciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("ClientesRepository")
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    List<Clientes> findByNombreContaining(String nombre);
    Clientes findByEmail(String email);
    List<Clientes> findByDireccion(Direcciones direccion);
    @Query("SELECT c.clienteId FROM Clientes c WHERE c.clienteId = :clienteId")
    Clientes findClienteByProyectoId(@Param("clienteId") Long clienteId);
}
