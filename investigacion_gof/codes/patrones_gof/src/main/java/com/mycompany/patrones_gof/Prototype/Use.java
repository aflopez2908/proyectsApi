/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patrones_gof.Prototype;

/**
 *
 * @author pipel
 */
public class Use {
    public static void main(String[] args) {
        ConcretePrototype prototype1 = new ConcretePrototype("Prototype1");
        ConcretePrototype clone1 = (ConcretePrototype) prototype1.clone();

        System.out.println("Original: " + prototype1.getName());
        System.out.println("Clone: " + clone1.getName());
    }
}

