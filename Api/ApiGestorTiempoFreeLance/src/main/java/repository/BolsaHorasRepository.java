/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import entity.BolsaHoras;
import entity.Proyectos;
import entity.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("BolsaHorasRepository")
public interface BolsaHorasRepository  extends JpaRepository<BolsaHoras, Integer>{
    List<BolsaHoras> findByUsuario(Usuarios usuario);
    List<BolsaHoras> findByProyecto(Proyectos proyecto);
    List<BolsaHoras> findByHorasRestantesGreaterThan(Integer horas);  
}
