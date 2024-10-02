/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Clientes;
import entity.Direcciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("ClientesRepository")
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    List<Clientes> findByNombreContaining(String nombre);
    Clientes findByEmail(String email);
    List<Clientes> findByDireccion(Direcciones direccion);
}
