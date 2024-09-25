/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.interfaz.ITask;
import com.example.demo.interfaz.ITaskService;

/**
 *
 * @author pipel
 */

@Service
public class TaskService implements ITaskService {
    
    @Autowired
    private ITask iasign;
    
    @Override
    public int Create(Task asign) {
        int row;
        try {
            row =  iasign.Create(asign);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
    @Override
    public List<Task> Read() {
        List<Task> list;
        try {
            list = iasign.Read();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
    
    @Override
    public int Update(Task asign) {
        int row;
        try {
            row =  iasign.Update(asign);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int Delete(int asign_id) {
        int row;
        try {
            row =  iasign.Delete(asign_id);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
    
    
}
