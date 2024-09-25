/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.entity.TransactionTask;
import com.example.demo.interfaz.ITransaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pipel
 */

@Repository
public class DaoImplementationTransaction implements ITransaction{
    
    @Autowired
    private JdbcTemplate jdbctemplateT;
    
    @Override
    public int Create(TransactionTask transaction) {
        String Query = "INSERT INTO [dbo].[Historial_Tareas] (tarea_id, cambio, fecha_cambio, usuario_id,vigente) VALUES (?, ?,?,?,?)";
        return jdbctemplateT.update(Query, new Object[]{transaction.getHistorial_id(),transaction.getTarea_id(),transaction.getUsuario_id(),transaction.getVigente()} );
    }
    
    @Override
    public List<TransactionTask> Read() {
        String Query = "select * from [dbo].[Usuarios]";
        return jdbctemplateT.query(Query, BeanPropertyRowMapper.newInstance(TransactionTask.class));  
    }
    
    //no esta implementado

    @Override
    public int Update(TransactionTask transaction) {
        String Query = "UPDATE [dbo].[Historial_Tareas] SET tarea_id = ?, cambio = ?, fecha_cambio = ?, usuario_id = ?, vigente = ? WHERE historial_id = ?;";
        return jdbctemplateT.update(Query, new Object[]{transaction.getHistorial_id(),transaction.getTarea_id(),transaction.getUsuario_id(),transaction.getVigente()});
    }
    
    @Override
    public int UpdateS(TransactionTask transaction) {
        String Query = "UPDATE [dbo].[Historial_Tareas] SET vigente = ? WHERE historial_id = ?;";
        return jdbctemplateT.update(Query, new Object[]{transaction.getHistorial_id(),transaction.getTarea_id(),transaction.getUsuario_id(),transaction.getVigente()});
    }
    //no esta implementado
    @Override
    public int Delete(String historial_id) {
        String Query = "DELETE FROM [dbo].[Usuarios] WHERE usuario_id = ?;";
        return jdbctemplateT.update(Query, new Object[]{historial_id});
    }   
    
    
    
    
    
    
    
}
