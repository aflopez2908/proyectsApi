/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo;

import java.util.List;

/**
 *
 * @author Seidor Colombia
 */
public interface IUserService {
    public int Create(User user);
    public List<User> Read();
    public int Update(User user);
    public int Delete(int usuario_id);    
}
