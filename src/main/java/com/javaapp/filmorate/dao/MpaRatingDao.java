package com.javaapp.filmorate.dao;

import com.javaapp.filmorate.model.MpaRating;

import java.util.List;

public interface MpaRatingDao {
    List<MpaRating> getMpaRatings();

    MpaRating getMpaRatingById(int id);
}
