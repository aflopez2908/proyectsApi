/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crud_since_0.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author pipel
 */


@Data
@Entity
@Table(name= "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long studantId;
    private String firstName;
    private String lastName;
    private String email;
    
    
    
    
}
