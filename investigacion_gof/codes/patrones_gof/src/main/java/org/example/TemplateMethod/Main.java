package org.example.TemplateMethod;

public class Main {
    public static void main(String[] args) {
        AbstractClass obj = new ConcreteClass();
        obj.templateMethod();
    }
}
