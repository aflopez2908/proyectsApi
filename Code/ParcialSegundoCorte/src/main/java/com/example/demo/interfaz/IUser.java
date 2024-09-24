package com.example.demo.interfaz;

import com.example.demo.entity.User;
import java.util.List;

/**
 *
 * @author Seidor Colombia
 */
public interface IUser {
    public int Create(User user);
    public List<User> Read();
    public int Update(User user);
    public int Delete(int usuario_id);    
}
