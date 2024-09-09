package org.example.Visitor;

class ConcreteVisitor implements Visitor {
    public void visit(ConcreteElementA elementA) {
        System.out.println("Visiting Element A");
    }

    public void visit(ConcreteElementB elementB) {
        System.out.println("Visiting Element B");
    }
}