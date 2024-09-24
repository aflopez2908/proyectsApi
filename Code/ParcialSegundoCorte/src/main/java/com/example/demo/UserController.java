/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Seidor Colombia
 */
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService iuserservice;
    
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@RequestBody User user){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = iuserservice.Create(user);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User Crete with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);
    }
    
    @GetMapping("/read")
    public ResponseEntity<List<User>> read() {
        var result  = iuserservice.Read();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/update")    
    public ResponseEntity<ServiceResponse> update(@RequestBody User user){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = iuserservice.Update(user);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User Update with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);        
    }

    @PostMapping("/delete/{id}")    
    public ResponseEntity<ServiceResponse> delete(@PathVariable int id){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = iuserservice.Delete(id);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User delete with success");
            System.out.println("New Ob");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);        
    }    
    
    
}
