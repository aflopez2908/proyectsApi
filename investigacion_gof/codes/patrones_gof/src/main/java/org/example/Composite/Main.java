package org.example.Composite;

public class Main {
    public static void main(String[] args) {
        Composite root = new Composite();
        root.add(new Leaf());

        Composite branch = new Composite();
        branch.add(new Leaf());
        branch.add(new Leaf());

        root.add(branch);
        root.operation();
    }
}
