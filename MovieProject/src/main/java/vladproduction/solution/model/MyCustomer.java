package vladproduction.solution.model;

public class MyCustomer {

    private String name;

    private ShopCard shopCard;

    public MyCustomer(String name, ShopCard shopCard) {
        this.name = name;
        this.shopCard = shopCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopCard getShopCard() {
        return shopCard;
    }

    public void setShopCard(ShopCard shopCard) {
        this.shopCard = shopCard;
    }
}
