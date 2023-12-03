package org.solution2.exporter;


import org.solution2.MyCustomer;
import org.solution2.ShopCard;



public class TextCustomerExporter implements CustomerExporter {
    @Override
    public String export(MyCustomer myCustomer) {
        ShopCard shopCard = myCustomer.getShopCard();
        return statementShopCard(shopCard, myCustomer.getName());
    }

    //to be printed as text value of receipt

    //TODO: calculate frequent rental points
    private String statementShopCard(ShopCard shopCard, String name) {
        double totalAmount = shopCard.calculatePrice();
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name+ "\n";
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
