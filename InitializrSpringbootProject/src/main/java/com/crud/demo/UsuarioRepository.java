/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.crud.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pipel
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
}
