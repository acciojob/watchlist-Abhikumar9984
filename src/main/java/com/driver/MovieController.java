package com.driver;


import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository repo;


    @Autowired
    private MovieService service;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {

        repo.addMovieToMovieDb(movie);
        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        repo.addDirectorToDirectorDb(director);
        return new ResponseEntity<>("New director added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addPair(@RequestParam String MovieName, @RequestParam String DirectorName) {
        repo.addMovieDirectorPair(MovieName, DirectorName);
        return new ResponseEntity<>("New movie-director pair added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovie(@PathVariable String name) {
        Movie movie = service.getMovie(name);
        System.out.println(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirector(@PathVariable String name) {
        Director director = service.getDirector(name);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<Movie>> getMovieByDirectorName(@PathVariable String director) {
        List<Movie> ans = service.getAllMovieByName(director);
        return new ResponseEntity<>(ans, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> pans = service.getAllMoviesAdded();
        return new ResponseEntity<>(pans, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteTheMoviesOfDirector(@RequestParam String name) {
        service.deleteAllMoviesOfaParticularDirector(name);
        return new ResponseEntity<>("deleted successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        service.deleteAllMoviesAndItsDirector();
        return new ResponseEntity<>("removed successfully", HttpStatus.CREATED);
    }
}
