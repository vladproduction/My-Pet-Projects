package org.example;

import java.util.List;

import static org.example.Movie.MovieType.*;


public class Main {

    public static void main(String[] args) {
        List<Rental> rentals = List.of(new Rental(new Movie("Rambo", REGULAR), 1),
                new Rental(new Movie("Lord of the Rings", NEW_RELEASE), 4),
                new Rental(new Movie("Harry Potter", CHILDRENS), 5));

        Customer customer = new Customer("John Doe", rentals);
        String statement = customer.statement();

        System.out.println(statement);
    }
}