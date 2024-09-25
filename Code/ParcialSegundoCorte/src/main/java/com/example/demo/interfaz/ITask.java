/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.interfaz;

import com.example.demo.entity.Task;
import java.util.List;

/**
 *
 * @author pipel
 */
public interface ITask {
    
    public int Create(Task asign);
    public List<Task> Read();
    public int Update(Task asign);
    public int Delete(int asign_id); 
    
}
