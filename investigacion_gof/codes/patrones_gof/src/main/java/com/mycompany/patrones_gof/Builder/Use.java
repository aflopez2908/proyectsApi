/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patrones_gof.Builder;

/**
 *
 * @author pipel
 */
public class Use{
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);

        director.construct();
        Product product = builder.getResult();
        product.show();
    }
}

