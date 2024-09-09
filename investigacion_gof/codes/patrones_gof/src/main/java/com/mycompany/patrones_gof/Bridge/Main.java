package org.example.Bridge;



public class Main {
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementorA();
        Abstraction abstraction = new Abstraction(implementor);
        abstraction.operation();
    }
}