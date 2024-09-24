/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.Asign;
import com.example.demo.interfaz.IAsign;
import com.example.demo.interfaz.IAsignService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pipel
 */

@Service
public class AsignService implements IAsignService {
    
    @Autowired
    private IAsign iasign;
    
    @Override
    public int Create(Asign asign) {
        int row;
        try {
            row =  iasign.Create(asign);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
    @Override
    public List<Asign> Read() {
        List<Asign> list;
        try {
            list = iasign.Read();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
    
    @Override
    public int Update(Asign asign) {
        int row;
        try {
            row =  iasign.Update(asign);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int Delete(int asign_id) {
        int row;
        try {
            row =  iasign.Delete(asign_id);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
    
    
}
