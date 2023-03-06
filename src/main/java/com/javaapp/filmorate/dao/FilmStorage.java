package com.javaapp.filmorate.dao;

import com.javaapp.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    List<Film> listFilms();

    Film getFilmById(int id);

    Film createFilm(Film film);

    Film updateFilm(Film film);

    void addLike(int id, int userId);

    void deleteLike(int id, int userId);

    List<Film> getMostPopularFilms(int count);
}
