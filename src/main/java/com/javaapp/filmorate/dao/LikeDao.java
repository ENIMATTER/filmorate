package com.javaapp.filmorate.dao;

public interface LikeDao {
    void addLikeToFilm(int id, int userId);

    void deleteLikeFromFilm(int id, int userId);
}
