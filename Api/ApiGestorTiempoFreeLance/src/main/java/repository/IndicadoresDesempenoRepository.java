/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.IndicadoresDesempeno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("IndicadoresDesempenoRepository")
public interface IndicadoresDesempenoRepository extends JpaRepository<IndicadoresDesempeno, Integer> {
    List<IndicadoresDesempeno> findByNombreContaining(String nombre);
}
