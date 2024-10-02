/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Chats;
import entity.Proyectos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ChatsRepository")
public interface ChatsRepository extends JpaRepository<Chats, Integer> {
    List<Chats> findByProyecto(Proyectos proyecto);
    List<Chats> findByNombreContaining(String nombre);
}
