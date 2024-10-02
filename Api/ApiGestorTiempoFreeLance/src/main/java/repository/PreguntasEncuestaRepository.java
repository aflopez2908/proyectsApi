/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Encuestas;
import entity.PreguntasEncuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("PreguntasEncuestaRepository")
public interface PreguntasEncuestaRepository extends JpaRepository<PreguntasEncuesta, Integer> {
    List<PreguntasEncuesta> findByEncuesta(Encuestas encuesta);
    List<PreguntasEncuesta> findByPreguntaContaining(String pregunta);
}
