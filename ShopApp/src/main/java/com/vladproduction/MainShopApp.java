package com.vladproduction;

public class MainShopApp {
    public static void main(String[] args) {
        System.out.println("Shop App is started.");

        int measurement = 8;
        System.out.println("MIN_PRICE: " + Clothing.MIN_PRICE);
        System.out.println("TAX_RATE: " + Clothing.TAX_RATE);

        Customer customer1 = new Customer("Pinky", measurement);

        Clothing[] items = {
                new Clothing("Blue Jacket", 20.5, "M"),
                new Clothing("Orange T-Shirt", 10.5, "S"),
                new Clothing("Green T-Shirt", 15.5, "L"),
                new Clothing("Blue T-Shirt", 10.5, "S"),
                new Clothing("Green Scarf", 5.0, "S")
        };

        int count = 0;
        int avg = 0;

        customer1.addItems(items);
        System.out.println("Customer is: " + customer1.getName() + ", " + customer1.getSize() + ", Total Clothing Cost: " + customer1.getTotalClothingCost());
        //looping through the items of the concrete customer:
        for (Clothing item : customer1.getItems()) {
            if (item.getSize().equals("XL")) {
                count++;
                System.out.println("Item: " + item.getDescription() + ", price = " + item.getPrice());
                avg += item.getPrice();
            }
        }
        try{
            avg = (count ==0) ? 0 : avg / count; //check if count is zero or not;
            avg = avg / count; // we do not have any XL, so got exception (/ by zero)
            System.out.println("AVG: " + avg + " COUNT: " + count); //if exception is caught this line will not execute
        }catch (ArithmeticException e){
            System.out.println("Not allowed to divide by '0'.");
        }

    }
}
