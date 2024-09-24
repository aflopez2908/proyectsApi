package com.example.demo;

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
