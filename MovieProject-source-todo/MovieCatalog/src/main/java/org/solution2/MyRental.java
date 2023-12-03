package org.solution2;



public class MyRental {

    private final MyMovie myMovie;
    private final int daysRented;

    public MyRental(MyMovie myMovie, int daysRented) {
        this.myMovie = myMovie;
        this.daysRented = daysRented;
    }

    public MyMovie getMyMovie() {
        return myMovie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
