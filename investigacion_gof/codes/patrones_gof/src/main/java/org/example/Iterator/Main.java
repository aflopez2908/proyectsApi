package org.example.Iterator;


public class Main {
    public static void main(String[] args) {
        ConcreteCollection<String> collection = new ConcreteCollection<>();
        collection.add("Item1");
        collection.add("Item2");

        for (String item : collection) {
            System.out.println(item);
        }
    }
}