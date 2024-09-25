/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.ServiceResponse;
import com.example.demo.entity.Transaction;
import com.example.demo.interfaz.ITransactionService;
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
@RequestMapping("api/v1/transaction")
@CrossOrigin("*")
public class TransactionController {
      
     
      @Autowired
    private ITransactionService itransactionservice;
    
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@RequestBody Transaction transaction){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = itransactionservice.Create(transaction);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("Task Crete with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);
    }
    
    @GetMapping("/read")
    public ResponseEntity<List<Transaction>> read() {
        var result  = itransactionservice.Read();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/update")    
    public ResponseEntity<ServiceResponse> update(@RequestBody Transaction transaction){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = itransactionservice.Update(transaction);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User Update with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);        
    }

    @PostMapping("/delete/{id}")    
    public ResponseEntity<ServiceResponse> delete(@PathVariable String id){
      ServiceResponse serviceResponse = new ServiceResponse();
      int result = itransactionservice.Delete(id);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("User delete with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);        
    }
    
}
