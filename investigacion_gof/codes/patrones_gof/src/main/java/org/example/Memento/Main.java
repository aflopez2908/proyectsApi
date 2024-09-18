package org.example.Memento;

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State #1");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #2");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("Restored State: " + originator.getState());
    }
}