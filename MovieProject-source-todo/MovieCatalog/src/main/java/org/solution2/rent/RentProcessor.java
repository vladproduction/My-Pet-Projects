package org.solution2.rent;

import org.solution2.MyCustomer;
import org.solution2.MyMovie;
import org.solution2.MyRental;
import org.solution2.ShopCard;
import org.solution2.repository.MovieRepository;

public class RentProcessor {

    private MovieRepository movieRepository;

    public RentProcessor(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void makeRent(String movieTitle, MyCustomer myCustomer, int daysRent){

        MyMovie currentMovie = movieRepository.findMovieByTitle(movieTitle);
        MyRental currentRental = new MyRental(currentMovie, daysRent);
        ShopCard currentShopCard = myCustomer.getShopCard();
        currentShopCard.add(currentRental);

    }
}
