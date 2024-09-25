/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.interfaz;
import com.example.demo.entity.Task;
import java.util.List;

public interface ITaskService {    
    public int Create(Task task);
    public List<Task> Read();
    public int UpdateStateId(Task task);
    public int Delete(int task_id);     
}
