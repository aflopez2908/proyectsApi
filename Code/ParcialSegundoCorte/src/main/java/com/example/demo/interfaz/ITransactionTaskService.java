/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.interfaz;

import com.example.demo.entity.ChangeStateTask;
import com.example.demo.entity.TransactionTask;
import java.util.List;

public interface ITransactionTaskService {

    public int Create(TransactionTask transaction);

    public List<TransactionTask> Read();

    public int UpdateS(TransactionTask transaction);

    public int Update(TransactionTask transaction);

    public int Delete(String historial_id);

    public List<TransactionTask> GetSpecific();
    
    public int ChageStatusTask(ChangeStateTask changestatetask);
    public int updateVigenciaByTareaId(int tareaId);
    
}
