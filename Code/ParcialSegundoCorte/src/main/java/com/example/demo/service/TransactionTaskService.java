/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.ChangeStateTask;
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
public class TransactionTaskService implements ITransactionTaskService {

    @Autowired
    private ITransactionTask itransaction;

    @Autowired
    private ITask itask;

    @Override
    public int Create(TransactionTask transaction) {
        int row;
        try {
            int createrow = itransaction.Create(transaction);
            row = createrow;
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    public int CreateAfter(TransactionTask transaction) {
        int row;
        try {
            int update = itransaction.UpdateS(transaction);
            int createrow = itransaction.Create(transaction);
//            int updatebyid = itask.UpdateStateId(task);

            row = createrow;
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
    public List<TransactionTask> GetSpecific() {
        List<TransactionTask> list;
        try {
            list = itransaction.GetSpecific();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public int Update(TransactionTask transaction) {
        int row;
        try {
            row = itransaction.Update(transaction);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int UpdateS(TransactionTask transaction) {
        int row;
        try {
            row = itransaction.UpdateS(transaction);

        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int Delete(String historial_id) {
        int row;
        try {
            row = itransaction.Delete(historial_id);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int ChageStatusTask(ChangeStateTask changestatetask) {
        int row = 1;
        int rowmodify = 0;
        try {
            List<Task> listaNombres = itask.ReadSpecificTask(changestatetask.getTarea_id());
            if (listaNombres != null && !listaNombres.isEmpty()) {
                // La lista tiene valores
                System.out.println("La lista tiene " + listaNombres.size() + " elementos.");
 
              TransactionTask transaction = new TransactionTask();
              transaction.setTarea_id(changestatetask.getTarea_id());
              transaction.setCambio("Cambio Tarea");
              transaction.setFecha_cambio("2024-09-25 05:21:53.310");
              transaction.setUsuario_id(Character.toString('1'));
              transaction.setVigente(1);
              transaction.setEstado_id(changestatetask.getEstado_id());
              rowmodify= itransaction.Create(transaction);
             
            } else {
                // La lista está vacía o es nula
                System.out.println("La lista no tiene valores.");
            }
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

}
