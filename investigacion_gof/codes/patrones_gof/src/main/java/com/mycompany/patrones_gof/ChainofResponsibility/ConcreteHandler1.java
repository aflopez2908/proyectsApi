package org.example.ChainofResponsibility;

class ConcreteHandler1 extends Handler {
    public void handleRequest(String request) {
        if (request.equals("Request1")) {
            System.out.println("Handled by ConcreteHandler1");
        } else if (next != null) {
            next.handleRequest(request);
        }
    }
}