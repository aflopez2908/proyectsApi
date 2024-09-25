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

@Service
public class TaskService implements ITaskService {
    
    @Autowired
    private ITask iasign;
    
    @Override
    public int Create(Task task) {
        int row;
        try {
            row =  iasign.Create(task);
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
    public int UpdateStateId(Task task) {
        int row;
        try {
            row =  iasign.UpdateStateId(task);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int Delete(int task_id) {
        int row;
        try {
            row =  iasign.Delete(task_id);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }            
}
