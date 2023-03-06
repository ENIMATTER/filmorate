package com.javaapp.filmorate.dao;

import com.javaapp.filmorate.model.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getGenres();

    Genre getGenreById(int id);
}
