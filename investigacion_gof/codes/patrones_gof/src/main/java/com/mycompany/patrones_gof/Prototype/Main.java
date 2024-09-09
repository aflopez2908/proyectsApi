package org.example.Prototype;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype original = new Prototype("Original");
        Prototype copy = original.clone();
        System.out.println(copy.name); // Original
    }
}