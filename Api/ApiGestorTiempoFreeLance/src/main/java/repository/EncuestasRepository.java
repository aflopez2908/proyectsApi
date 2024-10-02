 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Encuestas;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("EncuestasRepository")
public interface EncuestasRepository extends JpaRepository<Encuestas, Integer> {
    List<Encuestas> findByNombreContaining(String nombre);
    List<Encuestas> findByFechaCreacion(Date fechaCreacion);
}
