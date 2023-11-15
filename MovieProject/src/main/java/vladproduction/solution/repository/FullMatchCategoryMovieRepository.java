package vladproduction.solution.repository;



import vladproduction.solution.model.MyMovie;

import java.util.Set;

public class FullMatchCategoryMovieRepository extends MovieRepository{
    @Override
    protected boolean isSearchable(MyMovie movie, Set<String> searchCategories) {
        Set<String> movieCategories = movie.getCategories();
        return movieCategories.containsAll(searchCategories);
    }
}
