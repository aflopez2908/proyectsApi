/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.TransactionTask;
import com.example.demo.interfaz.ITask;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.interfaz.ITransactionTask;
import com.example.demo.interfaz.ITransactionTaskService;

/**
 *
 * @author pipel
 */

@Service
public class TransactionTaskService implements ITransactionTaskService{
    
    
    @Autowired
    private ITransactionTask itransaction;
    
    @Autowired
    private ITask itask;
    
    
    
    @Override
    public int Create(TransactionTask transaction, Task task) {
        int row;
        
        try {
            int createrow =  itransaction.Create(transaction);
            int updaterow= itask.UpdateStateId(task);
            
            row= createrow+updaterow;
            
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
    public int UpdateS(TransactionTask transaction) {
        int row;
        try {
            row =  itransaction.UpdateS(transaction);
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
