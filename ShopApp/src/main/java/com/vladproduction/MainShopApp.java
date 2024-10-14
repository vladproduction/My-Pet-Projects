package com.vladproduction;

public class MainShopApp {
    public static void main(String[] args) {

        double tax = 0.2;
        double total = 0.0;
        int measurement = 3;

        System.out.println("Shop App is started.");

        Customer c1 = new Customer();
        c1.setName("Pinky");
        c1.setSize("S");
        System.out.println("Customer is: " + c1.getName());

        Clothing item1 = new Clothing();
        Clothing item2 = new Clothing();
        Clothing item3 = new Clothing();

        Clothing[] items = {item1, item2, item3, new Clothing(), new Clothing()};

        item1.setDescription("Blue Jacket");
        item1.setPrice(20.9);
        item1.setSize("M");

        item2.setDescription("Orange T-Shirt");
        item2.setPrice(10.5);
        item2.setSize("S");

        item3.setDescription("Green T-Shirt");
        item3.setPrice(16.5);
        item3.setSize("L");

        items[3].setDescription("Blue T-Shirt");
        items[3].setPrice(10.5);
        items[3].setSize("S");

        items[4].setDescription("Green Scarf");
        items[4].setPrice(5.0);
        items[4].setSize("S");


        /*System.out.println("Item - 1: " + item1.description + ", " + item1.price + ", " + item1.size);
        System.out.println("Item - 2: " + item2.description + ", " + item2.price + ", " + item2.size);
        System.out.println("Item - 3: " + item3.description + ", " + item3.price + ", " + item3.size);*/

        /*total = (item1.price + item2.price * 2) * (1 + tax);
        System.out.println("Total = " + total);*/

        switch (measurement) {
            case 1:
            case 2:
            case 3:
                c1.setSize("S");
                break;
            case 4:
            case 5:
            case 6:
                c1.setSize("M");
                break;
            case 7:
            case 8:
            case 9:
                c1.setSize("L");
                break;
            default:
                c1.setSize("X");
        }

        for (Clothing item : items) {
            if(c1.getSize().equals(item.getSize())){
                total = total + item.getPrice();
                System.out.println("Item: " + item.getDescription() + ", " + item.getPrice() + ", " + item.getSize());
                total = total + total * tax;
                if(total > 15){
                    break;
                }
            }
        }
        System.out.println("Total = " + total);

    }
}
