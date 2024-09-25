/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.entity.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.interfaz.ITask;

@Repository
public class DAOImplementationTask implements ITask{    
     @Autowired
    private JdbcTemplate jdbctemplate;

    @Override
    public int Create(Task task) {
        String Query = "insert into [dbo].[Tareas] (proyecto_id, nombre, descripcion, estado_id, fecha_inicio, fecha_fin, asignado_a) VALUES (?,?,?,?,?,?,?)";
        return jdbctemplate.update(Query, new Object[]{task.getProyecto_id(),task.getNombre(),task.getDescripcion(),task.getEstado_id(),task.getFecha_inicio(),task.getFecha_fin(),task.getAsignado_a()});
    }       
    @Override
    public List<Task> Read() {
        String Query = "select * from [dbo].[Tareas]";
        return jdbctemplate.query(Query, BeanPropertyRowMapper.newInstance(Task.class));  
    }    
    @Override
    public int UpdateStateId(Task task) {
        String Query = "update [dbo].[Tareas] set estado_id = ? where  tarea_id = ?;";
        return jdbctemplate.update(Query, new Object[]{task.getEstado_id(),task.getTarea_id()});
    }
    //no esta implementado
    @Override
    public int Delete(int task_id) {
        String Query = "DELETE FROM [dbo].[Tareas] WHERE tarea_id = ?;";
        return jdbctemplate.update(Query, new Object[]{task_id});
    }
    
    @Override
    public int GetMax() {
    String Query = "SELECT MAX(tarea_id) FROM dbo.Tareas;";
    
    
    return jdbctemplate.queryForObject(Query, Integer.class);
}
}
