/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Informes;
import entity.VersionesInformes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("VersionesInformesRepository")
public interface VersionesInformesRepository extends JpaRepository<VersionesInformes, Integer> {
    List<VersionesInformes> findByInforme(Informes informe);
    List<VersionesInformes> findByVersion(String version);
}