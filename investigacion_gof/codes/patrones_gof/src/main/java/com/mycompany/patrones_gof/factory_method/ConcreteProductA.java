/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patrones_gof.factory_method;

/**
 *
 * @author pipel
 */
class ConcreteProductA implements Product {
    @Override
    public void create() {
        System.out.println("Product A created");
    }
}
