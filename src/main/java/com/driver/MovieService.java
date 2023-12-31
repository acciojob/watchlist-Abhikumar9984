package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository obj1;

    public Movie getMovie(String movieName){
        List<Movie> temp  = obj1.getAllMovie();
        for(Movie m : temp){
            if(m.getName().equals(movieName)){
                return m;
            }
        }
        return null;
    }

    public Director getDirector(String directorName){
        List<Director> temp  = obj1.getAllDirector();
        for(Director d: temp){
            if(d.getName().equals(directorName)){
                return d;
            }
        }
        return null;
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
