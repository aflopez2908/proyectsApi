/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import entity.EvaluacionesIndicadoresId;
import entity.EvaluacionesDesempeno;
import entity.EvaluacionesIndicadores;
import entity.IndicadoresDesempeno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("EvaluacionesIndicadoresRepository")
public interface EvaluacionesIndicadoresRepository extends JpaRepository<EvaluacionesIndicadores, EvaluacionesIndicadoresId> {
    List<EvaluacionesIndicadores> findByEvaluacion(EvaluacionesDesempeno evaluacion);
    List<EvaluacionesIndicadores> findByIndicador(IndicadoresDesempeno indicador);
}
