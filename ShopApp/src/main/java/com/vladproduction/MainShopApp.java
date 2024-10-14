package com.vladproduction;

public class MainShopApp {
    public static void main(String[] args) {
        System.out.println("Shop App is started.");

        int measurement = 8;

        Customer customer1 = new Customer("Pinky", measurement);

        Clothing[] items = {
                new Clothing("Blue Jacket", 20.9, "M"),
                new Clothing("Orange T-Shirt", 10.5, "S"),
                new Clothing("Green T-Shirt", 16.5, "L"),
                new Clothing("Blue T-Shirt", 10.5, "S"),
                new Clothing("Green Scarf", 5.0, "S")
        };

        customer1.addItems(items);
        System.out.println("Customer is: " + customer1.getName() + ", " + customer1.getSize() + ", Total Clothing Cost: " + customer1.getTotalClothingCost());
        //looping through the items of the concrete customer:
        for (Clothing item : customer1.getItems()) {
            System.out.println("Item: " + item.getDescription() + ", price = " + item.getPrice());
        }


    }
}
