package org.example.FactoryMethod;

class ConcreteProduct implements Product {
    public void create() {

        System.out.println("ConcreteProduct created");
    }
}