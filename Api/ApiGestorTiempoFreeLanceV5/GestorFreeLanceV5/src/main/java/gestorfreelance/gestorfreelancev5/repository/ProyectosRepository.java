package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.Clientes;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Long> {

    //get by nombre para no tener proyectos con nombre repetidos
    Proyectos findByNombre(String nombre);


    @Query("SELECT p.cliente FROM Proyectos p WHERE p.proyectoId = :proyectoId")
    Clientes findClienteByProyectoId(@Param("proyectoId") Long proyectoId);

}