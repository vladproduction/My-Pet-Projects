package org.solution2.repository;

import org.solution2.MyMovie;

import java.util.Set;

public class FullMatchCategoryMovieRepository extends MovieRepository{
    @Override
    protected boolean isSearchable(MyMovie movie, Set<String> searchCategories) {
        Set<String> movieCategories = movie.getCategories();
        return movieCategories.containsAll(searchCategories);
    }
}
