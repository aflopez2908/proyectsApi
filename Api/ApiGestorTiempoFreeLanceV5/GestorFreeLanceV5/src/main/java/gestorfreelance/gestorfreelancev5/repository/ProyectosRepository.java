package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {

    @Query("SELECT p.proyectoId FROM Proyecto p WHERE p.nombre = :nombre")
    Integer findIdByName(@Param("nombre") String nombre);
    Proyecto findByNombre(String nombre);
    Proyecto findByProyectoId(Integer id);
    @Query("SELECT p.cliente FROM Proyecto p WHERE p.proyectoId = :proyectoId")
    Cliente findClienteByProyectoId(@Param("proyectoId") Long proyectoId);

    long count(); // Total de proyectos


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Tarea WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Recurso WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Bolsa_Hora WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Facturacion WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Evento WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Informe WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Proyecto_Producto WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Actividad WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Chat WHERE proyecto_id = :proyectoId; " +
            "DELETE FROM Proyecto_Categoria WHERE proyecto_id = :proyectoId;"+
            "DELETE FROM Proyecto_Estado WHERE proyecto_id = :proyectoId;",

            nativeQuery = true)
    void eliminarRelacionesPorProyectoId(@Param("proyectoId") Long proyectoId);



}