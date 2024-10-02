/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Permisos;
import entity.Roles;
import entity.RolesPermisos;
import entity.RolesPermisosId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("RolesPermisosRepository")
public interface RolesPermisosRepository extends JpaRepository<RolesPermisos, RolesPermisosId> {
    List<RolesPermisos> findByRol(Roles rol);
    List<RolesPermisos> findByPermiso(Permisos permiso);
}
