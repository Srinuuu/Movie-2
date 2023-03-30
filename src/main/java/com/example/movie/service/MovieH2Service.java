package com.example.movie.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.movie.model.Movie;
import com.example.movie.model.MovieRowMapper;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;


@Service
public class MovieH2Service{

    @Autowired
    private JdbcTemplate db;

    public ArrayList<Movie> getMovies() {
       return (ArrayList<Movie>) db.query("SELECT * FROM movielist",new MovieRowMapper());
    }
    public Movie getMovieById(int movieId){
        try{
      return db.queryForObject("SELECT * FROM movielist where movieId=?",new MovieRowMapper(),movieId);
    }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public Movie addMovie(Movie movie){
        db.update("INSERT INTO movielist(movieName,leadActor) values(?,?)",movie.getMovieName(),movie.getLeadActor());
        return db.queryForObject("select * from movielist where movieName=? and leadActor=?",new MovieRowMapper(),movie.getMovieName(),movie.getLeadActor());
    }
    public Movie updateMovie(int movieId,Movie movie){
        if(movie.getMovieName()!=null){
            db.update("Update movielist set movieName=? where movieId=?",movie.getMovieName(),movieId);
        }
         if(movie.getLeadActor()!=null){
            db.update("Update movielist set leadActor=? where movieId=?",movie.getLeadActor(),movieId);
        }
       return getMovieById(movieId);
    }

    public void deleteMovie(int movieId){
      db.update("delete from movielist where movieId=?",movieId);
    }
}
