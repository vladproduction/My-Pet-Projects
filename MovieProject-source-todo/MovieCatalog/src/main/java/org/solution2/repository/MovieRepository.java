package org.solution2.repository;

import org.solution2.MyMovie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//template Pattern
public abstract class MovieRepository {

    //imaging that is like kind of DB:
    private List<MyMovie> movies = new ArrayList<>();

    public void add(MyMovie movie){
        movies.add(movie);
    }

    public MyMovie findMovieByTitle(String movieTitle){
        for (MyMovie movie:movies) {
            if(movie.getTitle().equals(movieTitle)){
                return movie;
            }
        }
        return null;
    }

    public List<MyMovie> findAllMovies(){
        return movies;
    }

    public List<MyMovie> findAllMoviesByCategories(Set<String> searchCategories){
        List<MyMovie> resList = new ArrayList<>();
        for (MyMovie m: movies) {
            if(isSearchable(m,searchCategories)){
                resList.add(m);
            }
        }
        return resList;
    }

    protected abstract boolean isSearchable(MyMovie movie, Set<String> searchCategories);


}
