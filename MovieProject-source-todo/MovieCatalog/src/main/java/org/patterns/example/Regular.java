package org.patterns.example;

public class Regular implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }

    @Override
    public double getBonus(int daysRented) {
        return 1;
    }
}
