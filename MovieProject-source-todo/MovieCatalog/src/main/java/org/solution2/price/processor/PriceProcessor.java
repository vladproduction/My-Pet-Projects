package org.solution2.price.processor;

import org.solution2.MyMovie;
import org.solution2.MyRental;

import java.util.List;
import java.util.Set;

public class PriceProcessor {

    public double calculatePrice(List<MyRental> rentalList){
        double sum = 0;
        for (MyRental rental:rentalList) {
            sum += calculatePrice(rental);
        }
        return sum;
    }

    public double calculatePrice(MyRental myRental){
        MyMovie movie = myRental.getMyMovie();
        Set<String> movieCategory = movie.getCategories();
        if(movieCategory.contains("REGULAR")){
            return calculateRegular(myRental);
        }if (movieCategory.contains("NEW_RELEASE")){
            return calculateNewRelease(myRental);
        }if (movieCategory.contains("CHILDRENS")){
            return calculateChild(myRental);
        }// to aad mo category must add more if
        return calculate(myRental);
    }

    private double calculateRegular(MyRental myRental){
        double price = 2;
        if(myRental.getDaysRented()>2){
            int daysAfter2 = myRental.getDaysRented()-2;
            double extraPrice = daysAfter2*1.5;
            price += extraPrice;
        }
        return price;
    }

    private double calculateNewRelease(MyRental myRental){
        return myRental.getDaysRented()*3;
    }
    private double calculateChild(MyRental myRental){
        double price = 1.5;
        if(myRental.getDaysRented()>3){
            int daysAfter3 = myRental.getDaysRented()-3;
            double extraPrice = daysAfter3*1.5;
            price += extraPrice;
        }
        return price;
    }

    private double calculate(MyRental myRental){
        return myRental.getDaysRented()*10;
    }

}

