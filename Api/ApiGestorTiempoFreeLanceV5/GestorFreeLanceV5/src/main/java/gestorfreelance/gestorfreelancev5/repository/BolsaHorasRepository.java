package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("BolsaHorasRepository")
public interface BolsaHorasRepository  extends JpaRepository<BolsaHora, Integer>{
/*    List<BolsaHora> findByProyecto(Proyecto proyecto);
    List<BolsaHora> findByHorasRestantesGreaterThan(Integer horas);
    Optional<BolsaHora> findByProyecto_ProyectoId(Integer proyectoId);
    Optional<BolsaHora> findByProyectoId(Integer proyectoId);*/
    List<BolsaHora> findByProyecto(Proyecto proyecto);
    List<BolsaHora> findByHorasRestantesGreaterThan(Integer horas);
    Optional<BolsaHora> findByProyecto_ProyectoId(Integer proyectoId);

    @Query("SELECT bh FROM BolsaHora bh WHERE bh.proyecto.proyectoId IN (SELECT p.proyectoId FROM Proyecto p JOIN p.tarea t WHERE t.tareaId IN (SELECT ht.tarea.tareaId FROM HistorialTarea ht WHERE ht.usuario.usuarioId = :usuarioId))")
    List<BolsaHora> findByProyecto_Tarea_Historial_UsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT bh FROM BolsaHora bh WHERE bh.proyecto.proyectoId IN (SELECT p.proyectoId FROM Proyecto p JOIN p.tarea t WHERE t.tareaId IN (SELECT ht.tarea.tareaId FROM HistorialTarea ht WHERE ht.usuario.usuarioId = :usuarioId AND ht.fechaCambio BETWEEN :fechaInicio AND :fechaFin))")
    List<BolsaHora> findByProyecto_Tarea_Historial_UsuarioIdAndFechaBetween(@Param("usuarioId") Long usuarioId, @Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

}
