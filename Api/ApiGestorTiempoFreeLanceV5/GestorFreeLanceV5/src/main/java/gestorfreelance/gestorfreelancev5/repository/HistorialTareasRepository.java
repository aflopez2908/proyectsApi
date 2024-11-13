package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.HistorialTarea;
import gestorfreelance.gestorfreelancev5.model.Tarea;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("HistorialTareasRepository")
public interface HistorialTareasRepository extends JpaRepository<HistorialTarea, UUID> {
    List<HistorialTarea> findByTarea(Tarea tarea);
    List<HistorialTarea> findByUsuario(Usuario usuario);
    List<HistorialTarea> findByFechaCambio(Date fechaCambio);
    //HistorialTarea findBytareaaAndVigente(Tarea tarea , int vigente);
    HistorialTarea findByTareaAndVigente(Tarea tarea, int vigente);

    @Query("SELECT ht FROM HistorialTarea ht WHERE ht.usuario.usuarioId = :usuarioId")
    List<HistorialTarea> findByUsuario_UsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT ht FROM HistorialTarea ht WHERE ht.usuario.usuarioId = :usuarioId AND ht.fechaCambio BETWEEN :fechaInicio AND :fechaFin")
    List<HistorialTarea> findByUsuario_UsuarioIdAndFechaCambioBetween(@Param("usuarioId") Long usuarioId, @Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT ht FROM HistorialTarea ht WHERE ht.tarea.proyecto.proyectoId = :proyectoId")
    List<HistorialTarea> findByTarea_Proyecto_ProyectoId(@Param("proyectoId") Long proyectoId);
}

