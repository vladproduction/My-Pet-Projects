package vladproduction.solution.rent;


import vladproduction.solution.model.MyCustomer;
import vladproduction.solution.model.MyMovie;
import vladproduction.solution.model.MyRental;
import vladproduction.solution.model.ShopCard;
import vladproduction.solution.repository.MovieRepository;

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
