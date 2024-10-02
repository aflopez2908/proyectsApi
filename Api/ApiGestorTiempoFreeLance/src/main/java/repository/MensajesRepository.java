/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Chats;
import entity.Mensajes;
import entity.Usuarios;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Seidor Colombia
 */
@Repository("MensajesRepository")
public interface MensajesRepository extends JpaRepository<Mensajes, Integer> {
    List<Mensajes> findByChat(Chats chat);
    List<Mensajes> findByUsuario(Usuarios usuario);
    List<Mensajes> findByFechaMensaje(Date fechaMensaje);
}
