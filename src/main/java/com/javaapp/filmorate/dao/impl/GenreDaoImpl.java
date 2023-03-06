package com.javaapp.filmorate.dao.impl;

import com.javaapp.filmorate.dao.GenreDao;
import com.javaapp.filmorate.model.Genre;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@AllArgsConstructor
@Component
public class GenreDaoImpl implements GenreDao {

    private static final String GET_GENRES = "SELECT * FROM genres";
    private static final String GET_GENRE_BY_ID = "SELECT * FROM genres WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> getGenres() {
        return jdbcTemplate.query(GET_GENRES, new BeanPropertyRowMapper<>(Genre.class));
    }

    @Override
    public Genre getGenreById(int id) {
        return jdbcTemplate.queryForObject(GET_GENRE_BY_ID, new BeanPropertyRowMapper<>(Genre.class), id);
    }
}
