/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.TiposRecurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("TiposRecursoRepository")
public interface TiposRecursoRepository extends JpaRepository<TiposRecurso, Integer> {
    List<TiposRecurso> findByNombreContaining(String nombre);
}
