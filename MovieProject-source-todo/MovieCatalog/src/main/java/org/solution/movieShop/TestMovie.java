package org.solution.movieShop;

import java.util.ArrayList;
import java.util.List;

import static org.solution.movieShop.Movie.MovieAgeLimit.AGE_LIMIT18;

public class TestMovie {
    public static void main(String[] args) {
        Movie dieHart = new Movie("Die Hart","Action",1988,"John Moore", AGE_LIMIT18);
        Movie dieHart2 = new Movie("Die Hart2","Action",1990,"John Moore", AGE_LIMIT18);
        Movie dieHart3 = new Movie("Die Hart3","Action",1995,"John Moore", AGE_LIMIT18);
        Movie dieHart4 = new Movie("Die Hart4","Action",2007,"John Moore", AGE_LIMIT18);

        List<Movie> movies = new ArrayList<>();
        movies.add(dieHart);
        movies.add(dieHart2);
        movies.add(dieHart3);
        movies.add(dieHart4);

        int count = 1;
        for (Movie movie: movies) {
            System.out.println(count+")"+movie.toString());
            count++;
        }

        System.out.println("------------------------");
        Formatter printFormatter = new PrintFormatter();
        Printer printDieHart = new MoviePrinter(dieHart);
        String material1 = printDieHart.print(printFormatter);
        System.out.println(material1);

        System.out.println("------------------------");
        Printer printDieHart2 = new MoviePrinter(dieHart2);
        String material2 = printDieHart2.print(printFormatter);
        System.out.println(material2);

        System.out.println("------html-------");
        Formatter htmlFormatter = new HTMLFormatter();
        Printer htmlPrint = new MoviePrinter(dieHart4);
        String material4 = htmlPrint.print(htmlFormatter);
        System.out.println(material4);



    }
}
