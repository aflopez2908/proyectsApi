/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.EvaluacionesDesempeno;
import entity.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("EvaluacionesDesempenoRepository")
public interface EvaluacionesDesempenoRepository extends JpaRepository<EvaluacionesDesempeno, Integer> {
    List<EvaluacionesDesempeno> findByUsuario(Usuarios usuario);
    List<EvaluacionesDesempeno> findByFechaEvaluacion(Date fechaEvaluacion);
}