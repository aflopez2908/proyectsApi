/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.interfaz;

import com.example.demo.entity.Task;
import com.example.demo.entity.TransactionTask;
import java.util.List;

public interface ITransactionTaskService {

    public int Create(TransactionTask transaction, Task task);

    public List<TransactionTask> Read();
    public int UpdateS(TransactionTask transaction);
    

    public int Update(TransactionTask transaction);
    
    

    public int Delete(String historial_id);
}
