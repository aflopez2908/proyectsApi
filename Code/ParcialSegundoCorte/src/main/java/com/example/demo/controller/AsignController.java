/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.ServiceResponse;
import com.example.demo.entity.Asign;
import com.example.demo.repository.IAsignService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("api/v1/asign")
@CrossOrigin("*")
public class AsignController {
    

    @Autowired
    private IAsignService iasignservice;
    
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@RequestBody Asign asign){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = iasignservice.Create(asign);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User Crete with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);
    }
    
    @GetMapping("/read")
    public ResponseEntity<List<Asign>> read() {
        var result  = iasignservice.Read();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/update")    
    public ResponseEntity<ServiceResponse> update(@RequestBody Asign asign){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = iasignservice.Update(asign);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User Update with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);        
    }

    @PostMapping("/delete/{id}")    
    public ResponseEntity<ServiceResponse> delete(@PathVariable int id){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = iasignservice.Delete(id);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User delete with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);        
    }    
    
  }


    

