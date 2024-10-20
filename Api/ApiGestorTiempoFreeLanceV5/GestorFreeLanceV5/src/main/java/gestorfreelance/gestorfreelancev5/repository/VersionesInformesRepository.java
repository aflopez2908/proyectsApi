package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Informe;
import gestorfreelance.gestorfreelancev5.model.VersionInforme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("VersionesInformesRepository")
public interface VersionesInformesRepository extends JpaRepository<VersionInforme, Integer> {
    List<VersionInforme> findByInforme(Informe informe);
    List<VersionInforme> findByVersion(String version);
}