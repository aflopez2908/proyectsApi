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
    
    
     public Long getStudentId() {
        return this.studantId;
    }

    public void setStudentId(Long studentId) {
        this.studantId = studentId;
    }

    // Getter y Setter para firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter y Setter para lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter y Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
    
    
    
}
