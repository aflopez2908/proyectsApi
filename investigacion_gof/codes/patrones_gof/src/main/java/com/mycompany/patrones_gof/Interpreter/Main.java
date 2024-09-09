package org.example.Interpreter;

public class Main {
    public static void main(String[] args) {
        Expression expr = new Plus(new Number(3), new Number(7));
        System.out.println("3 + 7 = " + expr.interpret(null)); // Output: 10
    }
}