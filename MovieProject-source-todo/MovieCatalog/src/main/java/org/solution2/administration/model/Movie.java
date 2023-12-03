package org.solution2.administration.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.cj.conf.StringProperty;


public class Movie extends RecursiveTreeObject<Movie> {

    StringProperty title, country, year, genre, category, rental;

    public Movie(StringProperty title, StringProperty country, StringProperty year,
                 StringProperty genre, StringProperty category, StringProperty rental) {
        this.title = title;
        this.country = country;
        this.year = year;
        this.genre = genre;
        this.category = category;
        this.rental = rental;
    }

    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty countryProperty() {
        return country;
    }
    public StringProperty yearProperty() {
        return year;
    }
    public StringProperty genreProperty() {
        return genre;
    }
    public StringProperty categoryProperty() {
        return category;
    }
    public StringProperty rentalProperty() {
        return rental;
    }

    public StringProperty getTitle() {
        return title;
    }

    public void setTitle(StringProperty title) {
        this.title = title;
    }

    public StringProperty getCountry() {
        return country;
    }

    public void setCountry(StringProperty country) {
        this.country = country;
    }

    public StringProperty getYear() {
        return year;
    }

    public void setYear(StringProperty year) {
        this.year = year;
    }

    public StringProperty getGenre() {
        return genre;
    }

    public void setGenre(StringProperty genre) {
        this.genre = genre;
    }

    public StringProperty getCategory() {
        return category;
    }

    public void setCategory(StringProperty category) {
        this.category = category;
    }

    public StringProperty getRental() {
        return rental;
    }

    public void setRental(StringProperty rental) {
        this.rental = rental;
    }


}
