package org.example.Decorator;

public class Main {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator(new ConcreteComponent());
        component.operation();
    }
}
