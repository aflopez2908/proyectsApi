package com.crud_since_0.demo;

import com.crud_since_0.demo.entity.Student;
import com.crud_since_0.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {
    
    @Autowired
    private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
        
    // Implementación de CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        // Crear una instancia de Student
        Student student = new Student();
        student.setFirstName("prueba");
        student.setLastName("prueba");
        student.setEmail("`prueba.doe@example.com");

        // Guardar en la base de datos
        studentRepository.save(student);
        //studentRepository.delete(student);

        System.out.println("Estudiante insertado con éxito: " + student);
    }
}
