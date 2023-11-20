package net.app;

public class Child extends Parent{

    public void jump(){
        System.out.println("Child.jump();");
    }

    @Override
    public void speak() {
        System.out.println("child.speak");
    }
}
