/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.entity;

import lombok.Data;

/**
 *
 * @author pipel
 */
@Data
public class Task {
    
    private int tarea_id;
    private int proyecto_id;
    private String nombre;
    private String descripcion;
    private int estado_id;
    private String fecha_inicio;
    private String fecha_fin; 
    private String asignado_a;
}
