package org.solution.movieShop;

public class Movie {

    private String title;
    private String genre;
    private int year;
    private String director;
    public MovieAgeLimit ageLimit;

    public enum MovieAgeLimit {
        ALL_AGE, AGE_LIMIT18
    }

    public Movie(String title, String genre, int year, String director, MovieAgeLimit ageLimit) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.ageLimit = ageLimit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public MovieAgeLimit getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(MovieAgeLimit ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return title +" "+ genre +" "+" "+ year +" "+ director +" "+" "+ ageLimit;
    }
}
