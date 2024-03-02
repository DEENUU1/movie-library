package com.crud.movielibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    private Movie movie;

    @GetMapping("/")
    public List<Movie> getall(){
        return movieRepository.getall();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") int id){
        return movieRepository.getById(id);
    }

    @PostMapping("/")
    public int addMovie(@RequestBody Movie movie){
        return movieRepository.addMovie(movie);
    }

    @PutMapping("/{id}")
    public int updateMovie(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
        Movie movie = movieRepository.getById(id);

        if (movie != null){
            movie.setMovie(updatedMovie.getMovie());
            movie.setRating(updatedMovie.getRating());

            movieRepository.updateMovie(movie);

            return 1;
        } else return -1;
    }
}
