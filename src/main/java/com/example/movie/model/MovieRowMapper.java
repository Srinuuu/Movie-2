package com.example.movie.model;

import com.example.movie.model.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MovieRowMapper implements RowMapper<Movie>{
    public Movie mapRow(ResultSet rs,int rowNum) throws SQLException{
        return new Movie(
            rs.getInt("movieId"),
            rs.getString("movieName"),
            rs.getString("leadActor")
        );
    }
}