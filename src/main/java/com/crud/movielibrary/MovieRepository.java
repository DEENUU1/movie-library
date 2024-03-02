package com.crud.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getall(){
        return jdbcTemplate.query("SELECT id, movie, rating FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, movie, rating FROM movie WHERE id = ?", BeanPropertyRowMapper.newInstance(Movie.class), id);
    }

    public int addMovie(Movie movie){
        jdbcTemplate.update("INSERT INTO movie (movie, rating) VALUES (?, ?)", movie.getMovie(), movie.getRating());
        return 1;
    }

    public int updateMovie(Movie movie){
        jdbcTemplate.update("UPDATE movie SET movie=?, rating=? WHERE id=?", movie.getMovie(), movie.getRating(), movie.getId());
        return 1;
    }
}
