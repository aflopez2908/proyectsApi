/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Asistentes;
import entity.Eventos;
import entity.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("AsistentesRepository")
public interface AsistentesRepository extends JpaRepository<Asistentes, Integer> {
    List<Asistentes> findByEvento(Eventos evento);
    List<Asistentes> findByUsuario(Usuarios usuario);    
}
