package org.app02;

public class Dog {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    public Dog(String name){
        System.out.println("Dog(String name)");
    }

    public Dog(){
        System.out.println("Dog()");
    }
}
