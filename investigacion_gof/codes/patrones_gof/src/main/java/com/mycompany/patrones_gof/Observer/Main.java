package org.example.Observer;

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observer1 = new ConcreteObserver("Observer1");
        Observer observer2 = new ConcreteObserver("Observer2");

        subject.attach(observer1);
        subject.attach(observer2);

        subject.notifyObservers("Event occurred");
    }
}