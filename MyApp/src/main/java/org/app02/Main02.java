package org.app02;

public class Main02 {

    public static void main(String[] args) {
        Dog d1 = new Dog("");
        Dog d2 = new Dog();

        d1.setName("mike");
        d2.setName("rokky");

        System.out.println(d1+d2.toString());



    }
}
