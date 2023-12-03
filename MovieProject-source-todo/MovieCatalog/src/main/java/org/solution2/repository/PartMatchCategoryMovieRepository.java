package org.solution2.repository;

import org.solution2.MyMovie;

import java.util.Set;

public class PartMatchCategoryMovieRepository extends MovieRepository{
    @Override
    protected boolean isSearchable(MyMovie movie, Set<String> searchCategories) {

        Set<String> movieCategories = movie.getCategories();
        for (String item:searchCategories) {
            if (movieCategories.contains(item)){
                return true;
            }
        }
        return false;
    }

}
