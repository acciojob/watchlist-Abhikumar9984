package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class MovieRepository {

    HashMap<String , Movie> movieDb = new HashMap<>();
    HashMap<String , Director> directorDb = new HashMap<>();
    HashMap<Director , List<Movie>>pairDb  = new HashMap<>();
    public void addMovieToMovieDb(Movie movie){
        movieDb.put(movie.getName() , movie);
    }

    public void addDirectorToDirectorDb(Director director){
        directorDb.put(director.getName(), director);
    }

    public void addMovieDirectorPair( String movieName , String directorName){
        if(pairDb.containsKey(directorDb.get(directorName))){
            List<Movie> list  = pairDb.get(directorDb.get(directorName));
            list.add(movieDb.get(movieName));
            pairDb.put(directorDb.get(directorName) , list);
        }
        Movie movie  =  movieDb.get(movieName);
        Director director  = directorDb.get(directorName);
        List<Movie> list  = new ArrayList<>();
        list.add(movie);
        pairDb.put(director , list);
    }

    public List<Movie> getAllMovie(){
        return movieDb.values().stream().toList();
    }

    public List<Director> getAllDirector(){
        return directorDb.values().stream().toList();
    }

    public List<Movie> getAllMovieByDirectorName(String name){
        return pairDb.get(directorDb.get(name));
    }

    public void deleteAllMoviesByDirectorName(String name){
       pairDb.remove(directorDb.get(name));
    }

    public void deleteAllDirectorAndMovies(){

        for(Director d : pairDb.keySet()){
                pairDb.remove(d);
        }
    }

    public Movie getMovieByName(String name){
        return movieDb.get(name);
    }

    public Director getDirectorByName(String name){
        return directorDb.get(name);
    }

}
