package com.javaapp.filmorate.controller;

import com.javaapp.filmorate.model.Genre;
import com.javaapp.filmorate.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenreService genreService;

    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable int id) {
        return genreService.getGenreById(id);
    }
}
