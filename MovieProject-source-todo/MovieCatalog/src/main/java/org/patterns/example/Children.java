package org.patterns.example;

public class Children implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

    @Override
    public double getBonus(int daysRented) {
        return 1;
    }
}
