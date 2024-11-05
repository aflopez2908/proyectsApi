package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends  JpaRepository<Proyecto, Long> {
    long count(); // Total de proyectos
    long countByEstado(String estado); // Contar proyectos por estado
    List<Proyecto> findAll(); // Obtener todos los proyectos
}
