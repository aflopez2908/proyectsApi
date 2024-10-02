/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.PreguntasEncuesta;
import entity.RespuestasEncuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("RespuestasEncuestaRepository")
public interface RespuestasEncuestaRepository extends JpaRepository<RespuestasEncuesta, Integer> {
    List<RespuestasEncuesta> findByPregunta(PreguntasEncuesta pregunta);
    List<RespuestasEncuesta> findByRespuestaContaining(String respuesta);
}
