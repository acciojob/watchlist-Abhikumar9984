package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository obj1;

    public Movie getMovie(String movieName){
        System.out.println(movieName);
        return obj1.getMovieByName(movieName);
    }

    public Director getDirector(String directorName){
        return obj1.getDirectorByName(directorName);
    }

    public List<Movie> getAllMovieByName(String directorName){
         return obj1.getAllMovieByDirectorName(directorName);
    }

    public List<Movie> getAllMoviesAdded(){
       return  obj1.getAllMovie();
    }

    public void deleteAllMoviesOfaParticularDirector(String name){
        obj1.deleteAllMoviesByDirectorName(name);
    }

    public void deleteAllMoviesAndItsDirector(){
        obj1.deleteAllDirectorAndMovies();
    }
}
