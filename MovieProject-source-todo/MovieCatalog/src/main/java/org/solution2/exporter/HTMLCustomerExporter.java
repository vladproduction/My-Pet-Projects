package org.solution2.exporter;

import org.solution2.MyCustomer;
import org.solution2.ShopCard;

public class HTMLCustomerExporter implements CustomerExporter {
    @Override
    public String export(MyCustomer myCustomer) {
        ShopCard shopCard = myCustomer.getShopCard();
        return statementShopCard(shopCard, myCustomer.getName());
    }

    //to be printed as HTML value of receipt
    //TODO: read template from resource
    //TODO: calculate frequent rental points
    private String statementShopCard(ShopCard shopCard, String name) {
        double totalAmount = shopCard.calculatePrice();
        int frequentRenterPoints = 0;
        String result = "<html>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <td>Rental Record for %s</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Amount owed is %s</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>You earned %s frequent renter points</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        return String.format(result,name,totalAmount,frequentRenterPoints);

    }
}
