package org.patterns.main;

import org.patterns.example.Customer;
import org.patterns.example.Movie;
import org.patterns.example.MovieTypeFactory;
import org.patterns.example.Rental;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MovieTypeFactory typeFactory = MovieTypeFactory.getInstance();
        List<Rental> rentals = List.of(new Rental(new Movie("Rambo", typeFactory.create("Regular")), 1),
                new Rental(new Movie("Lord of the Rings", typeFactory.create("NewRelease")), 4),
                new Rental(new Movie("Harry Potter", typeFactory.create("Children")), 5));

        Customer customer = new Customer("John Doe", rentals);
        String statement = customer.statement();

        System.out.println(statement);
    }
}

// Task:

// Уявімо, що нам треба додати нові фічі.
// По-перше, клієнт хоче виведення ще й у HTML.
// По-друге, клієнт хоче додати інші типи фільмів - наприклад, драми, комедії, трилери.
// По-третє, ми хочемо додати до фільмів нові характеристики (країна походження, короткий опис, режисер, актори)

// У поточну структуру ці правки лягають важко, тому нам доведеться її змінити.

// Створити можливість обліку декількох клієнтів, роботи з текстовими(!?) файлами для зберігання даних
// Програма повинна надавати можливості
// - перегляду каталога всіх фільмів,
// - додавання фільму,
// - пошуку за певними характеристиками
// - та ін.
// Створити зручне меню для доступу до функцій