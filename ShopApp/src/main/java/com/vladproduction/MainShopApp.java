package com.vladproduction;

public class MainShopApp {
    public static void main(String[] args) {

        double tax = 0.2, total = 0.0;
        int measurement = 3;

        System.out.println("Shop App is started.");

        Customer c1 = new Customer();
        c1.name = "Pinky";
        c1.size = "S";
        System.out.println("Customer is: " + c1.name);

        Clothing item1 = new Clothing();
        Clothing item2 = new Clothing();
        Clothing item3 = new Clothing();

        Clothing[] items = {item1, item2, item3};

        item1.description = "Blue Jacket";
        item1.price = 20.9;
        item1.size = "M";

        item2.description = "Orange T-Shirt";
        item2.price = 10.5;
        item2.size = "S";

        item3.description = "Orange T-Shirt";
        item3.price = 10.5;
        item3.size = "S";

        System.out.println("Item - 1: " + item1.description + ", " + item1.price + ", " + item1.size);
        System.out.println("Item - 2: " + item2.description + ", " + item2.price + ", " + item2.size);

        total = (item1.price + item2.price * 2) * (1 + tax);
        System.out.println("Total = " + total);

        switch (measurement) {
            case 1:
            case 2:
            case 3:
                c1.size = "S";
                break;
            case 4:
            case 5:
            case 6:
                c1.size = "M";
                break;
            case 7:
            case 8:
            case 9:
                c1.size = "L";
                break;
            default:
                c1.size = "X";
        }

    }
}
