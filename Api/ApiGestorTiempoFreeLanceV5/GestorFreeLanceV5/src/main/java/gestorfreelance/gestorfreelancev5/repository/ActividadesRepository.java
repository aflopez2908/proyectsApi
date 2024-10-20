package gestorfreelance.gestorfreelancev5.repository;


import java.sql.Date;
import java.util.List;

import gestorfreelance.gestorfreelancev5.model.Actividad;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ActividadesRepository")
public interface ActividadesRepository extends JpaRepository<Actividad, Integer>{
    List<Actividad> findByNombre(String nombre);
    List<Actividad> findByProyecto(Proyecto proyecto);
    List<Actividad> findByFechaActividad(Date fechaActividad);
}
