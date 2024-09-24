/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.interfaz.IUser;
import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.BeanProperty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository
public class DAOUserImplementacion implements IUser{

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Override
    public int Create(User user) {
        String Query = "INSERT INTO [dbo].[Usuarios] (nombre, email, contraseña, rol_id, fecha_creacion) VALUES (?, ?, ?,?,?)";
        return jdbctemplate.update(Query, new Object[]{user.getNombre(), user.getEmail(),user.getConstraseña(),user.getRol_id(),user.getFecha_creacion()});
    }

    @Override
    public List<User> Read() {
        String Query = "select * from [dbo].[Usuarios]";
        return jdbctemplate.query(Query, BeanPropertyRowMapper.newInstance(User.class));  
    }

    @Override
    public int Update(User user) {
        String Query = "UPDATE [dbo].[Usuarios] SET nombre = ?, email = ?, contraseña = ?, rol_id = ?, fecha_creacion = ? WHERE usuario_id = ?;";
        return jdbctemplate.update(Query, new Object[]{user.getNombre(), user.getEmail(),user.getConstraseña(),user.getRol_id(), user.getFecha_creacion(),user.getUsuario_id()});
    }

    @Override
    public int Delete(int usuario_id) {
        String Query = "DELETE FROM [dbo].[Usuarios] WHERE usuario_id = ?;";
        return jdbctemplate.update(Query, new Object[]{usuario_id});
    }   
}
