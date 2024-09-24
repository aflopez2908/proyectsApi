/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.interfaz;

import com.example.demo.entity.Transaction;
import java.util.List;


/**
 *
 * @author pipel
 */

public interface ITransaction {
    
    public int Create(Transaction trasaction);
    public List<Transaction> Read();
    public int Update(Transaction transaction);
    public int Delete(String historial_id);
    
    
}
