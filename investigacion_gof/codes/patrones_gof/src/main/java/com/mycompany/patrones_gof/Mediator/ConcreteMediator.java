package org.example.Mediator;

import java.util.ArrayList;

class ConcreteMediator implements Mediator {
    private ArrayList<Colleague> colleagues = new ArrayList<>();

    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    public void sendMessage(String message, Colleague colleague) {
        for (Colleague col : colleagues) {
            if (col != colleague) {
                System.out.println("Message to " + col + ": " + message);
            }
        }
    }
}