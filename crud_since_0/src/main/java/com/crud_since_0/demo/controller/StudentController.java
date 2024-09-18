/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crud_since_0.demo.controller;

import com.crud_since_0.demo.entity.Student;
import com.crud_since_0.demo.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pipel
 */

@RestController
@RequestMapping(path= "api/v1/students")
public class StudentController {
    
    @Autowired
    
    private StudentService studentService;
    
    @GetMapping
    public List<Student> getAll(){
        
        return studentService.getStudents();
    }
    
    
    @PostMapping
    public void saveUpdate(@RequestBody Student student){
        
         studentService.saveOrUpdate(student);
    }
    
    @DeleteMapping ("/{studentId}")
    public void delete(@PathVariable("studentId") Long studentId){
        
        studentService.delete(studentId);
    
    }
    
    
    
    
    
}
