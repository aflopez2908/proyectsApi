package org.example.AbstractFactory;

public class Main {
    public static void main(String[] args) {
        FurnitureFactory factory = new ModernFurnitureFactory();
        Chair chair = factory.createChair();
        Sofa sofa = factory.createSofa();

        chair.sitOn();
        sofa.lieOn();
    }
}