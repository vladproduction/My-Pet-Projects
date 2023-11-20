package net.app;

public class Child2 extends Parent{

    @Override
    public void speak() {
        System.out.println("child2.speak();");
    }
    public void run(){
        System.out.println("child2.run();");
    }
}
