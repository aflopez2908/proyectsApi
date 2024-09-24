/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Seidor Colombia
 */
@Service
public class UserService implements IUserService{
    @Autowired
    private IUser iuser;
    @Override
    public int Create(User user) {
        int row;
        try {
            row =  iuser.Create(user);
            System.out.println("Hola mundo");
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public List<User> Read() {
        List<User> list;
        try {
            list = iuser.Read();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public int Update(User user) {
        int row;
        try {
            row =  iuser.Update(user);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int Delete(int usuario_id) {
        int row;
        try {
            row =  iuser.Delete(usuario_id);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
}
