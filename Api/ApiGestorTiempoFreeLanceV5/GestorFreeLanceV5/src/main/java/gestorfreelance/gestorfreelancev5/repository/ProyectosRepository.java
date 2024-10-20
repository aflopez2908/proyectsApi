package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {

    //get by nombre para no tener proyectos con nombre repetidos
    Proyecto findByNombre(String nombre);


    @Query("SELECT p.cliente FROM Proyecto p WHERE p.proyectoId = :proyectoId")
    Cliente findClienteByProyectoId(@Param("proyectoId") Long proyectoId);

}