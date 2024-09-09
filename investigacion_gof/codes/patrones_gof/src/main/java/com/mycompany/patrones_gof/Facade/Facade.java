package org.example.Facade;

class Facade {
    private Subsystem1 subsystem1;
    private Subsystem2 subsystem2;

    public Facade() {
        subsystem1 = new Subsystem1();
        subsystem2 = new Subsystem2();
    }

    public void performOperations() {
        subsystem1.operation1();
        subsystem2.operation2();
    }
}