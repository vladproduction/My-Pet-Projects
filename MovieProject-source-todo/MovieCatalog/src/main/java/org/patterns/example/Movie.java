package org.patterns.example;

public class Movie {
    private final String title;
    private MovieType priceCode;

    public Movie(String title, MovieType priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount(int daysRented) {
        return getPriceCode().getAmount(daysRented);
    }

    public double getBonus(int daysRented) {
        return getPriceCode().getBonus(daysRented);
    }
}