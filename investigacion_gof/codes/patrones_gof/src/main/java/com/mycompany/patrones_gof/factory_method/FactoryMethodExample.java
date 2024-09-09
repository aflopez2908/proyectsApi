/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patrones_gof.factory_method;

/**
 *
 * @author pipel
 */
public class FactoryMethodExample {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        creatorA.someOperation(); // Output: Product A created

        Creator creatorB = new ConcreteCreatorB();
        creatorB.someOperation(); // Output: Product B created
    }
}
