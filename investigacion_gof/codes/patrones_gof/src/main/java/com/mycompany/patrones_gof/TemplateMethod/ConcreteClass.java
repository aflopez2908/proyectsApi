package org.example.TemplateMethod;

class ConcreteClass extends AbstractClass {
    protected void primitiveOperation1() {
        System.out.println("Operation1 in ConcreteClass");
    }

    protected void primitiveOperation2() {
        System.out.println("Operation2 in ConcreteClass");
    }
}
