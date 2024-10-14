package com.vladproduction;

public class MainShopApp {
    public static void main(String[] args) {

        int measurement = 8;

        System.out.println("Shop App is started.");

        Customer c1 = new Customer();
        c1.setName("Pinky");
//        c1.setSize("S");
        c1.setSize(measurement);


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

        c1.addItems(items);
        System.out.println("Customer is: " + c1.getName() + ", " + c1.getSize() + ", Total Clothing Cost: " + c1.getTotalClothingCost());
        //looping through the items of the concrete customer:
        for (Clothing item : c1.getItems()) {
            System.out.println("Item: " + item.getDescription() + ", price = " + item.getPrice());
        }


    }
}
