package org.example.ChainofResponsibility;

class ConcreteHandler2 extends Handler {
    public void handleRequest(String request) {
        if (request.equals("Request2")) {
            System.out.println("Handled by ConcreteHandler2");
        } else if (next != null) {
            next.handleRequest(request);
        }
    }
}
