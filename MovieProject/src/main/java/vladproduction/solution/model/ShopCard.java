package vladproduction.solution.model;



import vladproduction.solution.price.processor.PriceProcessor;

import java.util.ArrayList;
import java.util.List;

public class ShopCard {

    private List<MyRental> basket = new ArrayList<>();

    public void add(MyRental rent){
        basket.add(rent);
    }

    //TODO: add unit test
    public void remove(MyRental rent){
        basket.remove(rent);
    }

    public double calculatePrice(){
        PriceProcessor priceProcessor = new PriceProcessor();
        return priceProcessor.calculatePrice(basket);
    }


}
