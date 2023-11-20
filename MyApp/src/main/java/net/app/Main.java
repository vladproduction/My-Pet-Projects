package net.app;

public class Main {

    public static void main(String[] args) {
        Parent w = new Parent();
        w.speak();
        Child c = new Child();
        c.jump();
        c.speak();
        Parent w2 = new Child();
        w2.speak();
        //w2.jump  not allowed M from Child, only by Parent
        System.out.println("------------------------------------------");
        Parent a = new Parent();
        a.speak();
        Child b = new Child();
        b.jump();
        b.speak();
        Parent d = new Child();// from which class implements M new child/(no jump M)
        d.speak();
        System.out.println("------------------------------------------------");
        Child2 child2 = new Child2();
        child2.speak();
        child2.run();
        System.out.println("-----------------------------------------------");
        Parent a1 = new Parent();
        Child a2 = new Child();
        Child2 a3 = new Child2();
        conversation(a1);
        conversation(a2);
        conversation(a3);
        Child b1 = new Child();
       conversation2(b1);
    }
    private static void conversation (Parent parent){// we can use any class Parent or Child
        parent.speak();
    }
    private static void conversation2 (Child child){// we can use only Child
        child.speak();
        child.jump();
    }

}
