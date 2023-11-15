package vladproduction.solution.exporter;


import vladproduction.solution.model.MyCustomer;
import vladproduction.solution.model.ShopCard;
import vladproduction.solution.utils.Utils;

public class HTMLCustomerExporter implements CustomerExporter {
    @Override
    public String export(MyCustomer myCustomer) {
        ShopCard shopCard = myCustomer.getShopCard();
        return statementShopCard(shopCard, myCustomer.getName());
    }

    //to be printed as HTML value of receipt

    private String statementShopCard(ShopCard shopCard, String name) {
        double totalAmount = shopCard.calculatePrice();
        int frequentRenterPoints = 0;
        String template = Utils.readContent("/template.html");

        return String.format(template,name,totalAmount,frequentRenterPoints);

    }
}
