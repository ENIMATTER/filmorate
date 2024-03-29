package com.javaapp.filmorate.controller;

import com.javaapp.filmorate.model.MpaRating;
import com.javaapp.filmorate.service.MpaRatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/mpa")
public class MpaRatingController {

    private MpaRatingService mpaRatingService;

    @GetMapping
    public List<MpaRating> getGenres() {
        return mpaRatingService.getMpaRatings();
    }

    @GetMapping("/{id}")
    public MpaRating getGenreById(@PathVariable int id) {
        return mpaRatingService.getMpaRatingById(id);
    }
}
