/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.entity.Asign;
import com.example.demo.interfaz.IAsign;
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
public class DAOImplementationAsign implements IAsign{
    
     @Autowired
    private JdbcTemplate jdbctemplate;

    @Override
    //no esta implementado
    public int Create(Asign asign) {
        String Query = "INSERT INTO [dbo].[Usuarios] (nombre, email, contraseña, rol_id, fecha_creacion) VALUES (?, ?, ?,?,?)";
        return jdbctemplate.update(Query, new Object[]{asign.getNombre(), asign.getProyecto_id(),asign.getDescripcion(),asign.getEstado_id()});
    }
    
    

    @Override
    public List<Asign> Read() {
        String Query = "select * from [dbo].[Tareas]";
        return jdbctemplate.query(Query, BeanPropertyRowMapper.newInstance(Asign.class));  
    }
    
    //no esta implementado
    @Override
    public int Update(Asign asign) {
        String Query = "UPDATE [dbo].[Usuarios] SET nombre = ?, email = ?, contraseña = ?, rol_id = ?, fecha_creacion = ? WHERE usuario_id = ?;";
        return jdbctemplate.update(Query, new Object[]{asign.getNombre(), asign.getProyecto_id(),asign.getDescripcion(),asign.getEstado_id()});
    }
    //no esta implementado
    @Override
    public int Delete(int usuario_id) {
        String Query = "DELETE FROM [dbo].[Usuarios] WHERE usuario_id = ?;";
        return jdbctemplate.update(Query, new Object[]{usuario_id});
    }


     
    
}
