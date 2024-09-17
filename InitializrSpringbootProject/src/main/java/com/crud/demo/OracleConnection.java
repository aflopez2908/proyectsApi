/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crud.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pipel
 */
public class OracleConnection {
    private static OracleConnection instance;
    private Connection connection;
    
    private OracleConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@//localhost:1521/ORCL", "SYSTEM", "AFLRaflr2908??");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static OracleConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new OracleConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
}

