/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.entity;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionTask {
    private String historial_id;
    private int tarea_id;
    private String cambio;
    private String fecha_cambio;
    private String usuario_id;
    private int vigente;
    private int estado_id;
}
