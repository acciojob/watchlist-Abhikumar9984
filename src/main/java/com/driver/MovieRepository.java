package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class MovieRepository {

    HashMap<String , Movie> movieDb = new HashMap<>();
    HashMap<String , Director> directorDb = new HashMap<>();
    HashMap<Director , Movie> pairDb  = new HashMap<>();
    public void addMovieToMovieDb(Movie movie){
        movieDb.put(movie.getName() , movie);
    }

    public void addDirectorToDirectorDb(Director director){
        directorDb.put(director.getName(), director);
    }

    public void addMovieDirectorPair( String movieName , String directorName){
        Movie movie  =  movieDb.get(movieName);
        Director director  = directorDb.get(directorName);
        pairDb.put(director , movie);
    }

    public List<Movie> getAllMovie(){
        return movieDb.values().stream().toList();
    }

    public List<Director> getAllDirector(){
        return directorDb.values().stream().toList();
    }

    public List<Movie> getAllMovieByDirectorName(String name){
        List<Movie> pans  = new ArrayList<>();

        for(Director d : pairDb.keySet()){
            if(d.getName().equals(name)){
                pans.add(pairDb.get(d));
            }
        }
        return pans;
    }

    public void deleteAllMoviesByDirectorName(String name){
        List<Movie> temp  = new ArrayList<>();
        for(Director d : pairDb.keySet()){
            if(d.getName().equals(name)){
                temp.add(pairDb.get(d));
                pairDb.remove(d);
            }
        }

        for(Movie m : temp){
            if(movieDb.containsKey(m.getName())){
                movieDb.remove(m.getName());
            }
        }

        for(String d : directorDb.keySet()){
            if(d.equals(name))
            {
                directorDb.remove(d);
                break;
            }
        }
    }

    public void deleteAllDirectorAndMovies(){
        List<Movie> temp  = new ArrayList<>();
        for(Director d : pairDb.keySet()){
                temp.add(pairDb.get(d));
                pairDb.remove(d);
        }

        for(Movie m : temp){
            if(movieDb.containsKey(m.getName())){
                movieDb.remove(m.getName());
            }
        }

        for(String d : directorDb.keySet()){
                directorDb.remove(d);
        }
    }
}
