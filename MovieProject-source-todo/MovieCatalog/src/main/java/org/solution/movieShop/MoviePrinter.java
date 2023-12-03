package org.solution.movieShop;

import java.util.ArrayList;
import java.util.List;

public class MoviePrinter extends Printer{

    private Movie movie;

    public MoviePrinter(Movie movie){
        this.movie = movie;
    }
    @Override
    protected List<Info> getShowInfo() {
        List<Info> infoList = new ArrayList<>();
        infoList.add(new Info("Film title", movie.getTitle()));
        infoList.add(new Info("Genre", movie.getGenre()));
        infoList.add(new Info("Director", movie.getDirector()));
        infoList.add(new Info("Year", movie.getYear()+""));

        return infoList;
    }

    @Override
    protected String getHeader() {
        return String.valueOf(movie.getAgeLimit());
    }
}
