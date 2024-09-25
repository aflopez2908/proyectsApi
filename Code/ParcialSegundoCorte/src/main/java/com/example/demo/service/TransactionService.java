/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.TransactionTask;
import com.example.demo.interfaz.ITransaction;
import com.example.demo.interfaz.ITransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pipel
 */

@Service
public class TransactionService implements ITransactionService{
    
    
    @Autowired
    private ITransaction itransaction;
    
    @Override
    public int Create(TransactionTask transaction) {
        int row;
        try {
            row =  itransaction.Create(transaction);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
    @Override
    public List<TransactionTask> Read() {
        List<TransactionTask> list;
        try {
            list = itransaction.Read();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
    
    @Override
    public int Update(TransactionTask transaction) {
        int row;
        try {
            row =  itransaction.Update(transaction);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int Delete(String historial_id) {
        int row;
        try {
            row =  itransaction.Delete(historial_id);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
    
    
}
