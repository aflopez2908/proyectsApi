/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crud_since_0.demo.controller;

import com.crud_since_0.demo.entity.Student;
import com.crud_since_0.demo.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pipel
 */

@RestController
@RequestMapping(path= "api/v1/students")
public class StudentController {

    public StudentController() {
        this.studentService = studentService;
    }
    
    @Autowired
    
    public List<Student> getAll(){
        private final StudentService studentService;
        return 
    
    }
    
}
