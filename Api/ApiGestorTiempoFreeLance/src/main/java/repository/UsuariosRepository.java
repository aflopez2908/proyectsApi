/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Usuarios;
import entity.Roles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("UsuariosRepository")
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    List<Usuarios> findByNombreContaining(String nombre);
    Usuarios findByEmail(String email);
    List<Usuarios> findByRol(Roles rol);
}