/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entity.Proyectos;

/**
 *
 * @author pipel
 */
@Repository
public interface ProyectoRepository extends JpaRepository<Proyectos, Long> {
}
