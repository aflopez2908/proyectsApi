package gestorfreelance.gestorfreelancev5.repository;


import java.sql.Date;
import java.util.List;

import gestorfreelance.gestorfreelancev5.model.Actividades;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ActividadesRepository")
public interface ActividadesRepository extends JpaRepository<Actividades, Integer>{
    List<Actividades> findByNombre(String nombre);
    List<Actividades> findByProyecto(Proyectos proyecto);
    List<Actividades> findByFechaActividad(Date fechaActividad);   
}
