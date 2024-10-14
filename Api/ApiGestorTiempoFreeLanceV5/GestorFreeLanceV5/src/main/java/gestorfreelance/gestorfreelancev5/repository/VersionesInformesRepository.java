package gestorfreelance.gestorfreelancev5.repository;

import gestorfreelance.gestorfreelancev5.model.Informes;
import gestorfreelance.gestorfreelancev5.model.VersionesInformes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("VersionesInformesRepository")
public interface VersionesInformesRepository extends JpaRepository<VersionesInformes, Integer> {
    List<VersionesInformes> findByInforme(Informes informe);
    List<VersionesInformes> findByVersion(String version);
}