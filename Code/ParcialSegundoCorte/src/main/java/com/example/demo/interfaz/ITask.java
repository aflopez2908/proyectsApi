/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.interfaz;

import com.example.demo.entity.Asign;
import java.util.List;

/**
 *
 * @author pipel
 */
public interface IAsign {
    
    public int Create(Asign asign);
    public List<Asign> Read();
    public int Update(Asign asign);
    public int Delete(int asign_id); 
    
}
