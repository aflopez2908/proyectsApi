/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.entity.Transaction;
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
    public int Create(Transaction transaction) {
        String Query = "INSERT INTO [dbo].[Usuarios] (nombre, email, contraseña, rol_id, fecha_creacion) VALUES (?, ?, ?,?,?)";
        return jdbctemplateT.update(Query, new Object[]{transaction.getHistorial_id(),transaction.getTarea_id(),transaction.getUsuario_id(),transaction.getVigente()} );
    }
    
    @Override
    public List<Transaction> Read() {
        String Query = "select * from [dbo].[Usuarios]";
        return jdbctemplateT.query(Query, BeanPropertyRowMapper.newInstance(Transaction.class));  
    }

    @Override
    public int Update(Transaction transaction) {
        String Query = "UPDATE [dbo].[Usuarios] SET nombre = ?, email = ?, contraseña = ?, rol_id = ?, fecha_creacion = ? WHERE usuario_id = ?;";
        return jdbctemplateT.update(Query, new Object[]{transaction.getHistorial_id(),transaction.getTarea_id(),transaction.getUsuario_id(),transaction.getVigente()});
    }

    @Override
    public int Delete(String historial_id) {
        String Query = "DELETE FROM [dbo].[Usuarios] WHERE usuario_id = ?;";
        return jdbctemplateT.update(Query, new Object[]{historial_id});
    }   
    
    
    
    
    
    
    
}
