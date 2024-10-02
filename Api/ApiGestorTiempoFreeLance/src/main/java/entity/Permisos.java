/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Seidor Colombia
 */
@Entity
@Table(name = "Permisos")
public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permisoId;
    @Column(nullable = false)
    private String nombre;
}
