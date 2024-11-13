package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {


    Proyecto findByNombre(String nombre);
    Proyecto findByProyectoId(Integer id);
    @Query("SELECT p.cliente FROM Proyecto p WHERE p.proyectoId = :proyectoId")
    Cliente findClienteByProyectoId(@Param("proyectoId") Long proyectoId);

    long count(); // Total de proyectos

}