package gestorfreelance.gestorfreelancev5.repository;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Long> {
}