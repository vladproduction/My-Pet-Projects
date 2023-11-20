package org.app;

import java.util.ArrayList;
import java.util.List;

public class MainCar {
    public static void main(String[] args) {
        Car car = new Car();
        car.setColor("white");
        car.setPrice(1000);

        Car car1 = new Car();
        car1.setColor("red");
        car1.setPrice(1000);

        boolean result = isSame(car, car1);
        System.out.println("result = " + result);

        boolean samePriceResult = isSamePrice(car, car1);
        System.out.println("samePrice = " + samePriceResult);

        boolean sameColorResult = isSameColor(car, car1);
        System.out.println("sameColorResult=" + sameColorResult);
        System.out.println("------------------------------------------------------");

        Car bmw = new Car("red",2000);
        Car audi = new Car("green",3000);
        Car ford = new Car("black", 4000);

        List<Car> carSalon = new ArrayList();
        carSalon.add(bmw);
        carSalon.add(audi);
        carSalon.add(ford);

        Car lifan = new Car("red2", 2001);
        boolean unique = isUnique(carSalon, lifan);
        System.out.println("result=" + unique);

        boolean isUniqueColor = isUniqueColor(carSalon, lifan);
        System.out.println("isUniqueColor = " + isUniqueColor);

        boolean isSamePrice = isSamePrice(carSalon, lifan);
        System.out.println("isSamePrice = "+isSamePrice);


    }

    private static boolean isSame(Car a, Car b) {
        boolean sameColor = a.getColor().equals(b.getColor());
        boolean samePrice = a.getPrice() == b.getPrice(); //cause int
        return sameColor && samePrice;
    }

    private static boolean isSamePrice(Car a, Car b) {

        boolean samePrice = a.getPrice() == b.getPrice(); //cause int
        return samePrice;
    }

    private static boolean isSameColor(Car a, Car b) {

        boolean sameColor = a.getColor().equals(b.getColor());
        return sameColor;
    }

    private static boolean isUnique(List<Car> carList, Car current){
        boolean result = true;
        for(int i = 0;i<carList.size();i++){
            Car myCar = carList.get(i);
            boolean sameCar = isSame(myCar, current);
            if(sameCar){
                result = false;
            }
        }

        return result;
    }

    private static boolean isUniqueColor(List<Car> carSalon, Car candidate){

        boolean result = true;
        for (int i = 0;i<carSalon.size();i++){
            Car car1 = carSalon.get(i);//first car on i place
            //car1.getColor().equals(candidate.getColor());
            boolean sameColor = isSameColor(car1, candidate);
            if(sameColor){
                result = false;
            }
        }
        return result;
    }
    private static boolean isSamePrice(List<Car> carSalon, Car candidate){
        boolean result = true;
        for (int i = 0;i<carSalon.size();i++){
            Car car2 = carSalon.get(i);
            /*boolean isSamePrice = isSamePrice(car2,candidate); helping M-->isSamePrice
            if(isSamePrice){
                result=false;
            }*/
            if(isSamePrice(car2,candidate)){ //we can do so
                result=false;
            }
        }

        return result;
    }

}
