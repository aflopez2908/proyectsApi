/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crud_since_0.demo.service;

import com.crud_since_0.demo.entity.Student;
import com.crud_since_0.demo.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pipel
 */

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    
    public List<Student> getStudents(){
    
        return studentRepository.findAll();
    }
    
      public Optional<Student> getStudents(Long id){
    
        return studentRepository.findById(id);
    }
      
      public void saveOrUpdate(Student student){
          
          studentRepository.save(student);
      
      }
      
      public void delete(Long id){
      
          studentRepository.deleteById(id);
      }
    
}
