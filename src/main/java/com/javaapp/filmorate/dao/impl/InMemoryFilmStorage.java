package com.javaapp.filmorate.dao.impl;

import com.javaapp.filmorate.dao.FilmStorage;
import com.javaapp.filmorate.exeption.NotFoundException;
import com.javaapp.filmorate.exeption.ValidationException;
import com.javaapp.filmorate.model.Film;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Data
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final InMemoryUserStorage inMemoryUserStorage;
    private int filmsIdCount = 0;
    private final Map<Integer, Film> films = new HashMap<>();
    private Map<Integer, List<Integer>> likes = new HashMap<>();


    @Override
    public List<Film> listFilms() {
        log.debug("Текущее количество фильмов: {}", films.size());
        return new ArrayList<>(films.values());
    }

    @Override
    public Film getFilmById(int id) {
        log.debug("Текущий фильм {}", films.get(id));
        return films.get(id);
    }

    @Override
    public Film createFilm(@NonNull Film film) {
        addNewId(film);
        films.put(film.getFilmId(), film);
        log.debug("Сохранен фильм: {}", film);
        return film;
    }

    @Override
    public Film updateFilm(@NonNull Film film) {
        if (films.containsKey(film.getFilmId())) {
            films.put(film.getFilmId(), film);
            return film;
        } else {
            log.warn("Ошибка при обновлении фильма: {}", film);
            throw new NotFoundException("Ошибка изменения фильма, проверьте корректность данных.");
        }
    }

    @Override
    public void addLike(int id, int userId) {
        if (idValidation(id) && userId > 0 && inMemoryUserStorage.getUsers().containsKey(userId)) {
            Film film = films.get(id);
            List<Integer> filmLikes = likes.get(id);
            filmLikes.add(userId);
            likes.put(id, filmLikes);
            log.debug("Фильму {} поставили лайк.", film);
        } else {
            log.warn("Ошибка при добавлении лайка фильму.");
            throw new ValidationException("Ошибка добавления лайка, проверьте корректность данных.");
        }
    }

    @Override
    public void deleteLike(int id, int userId) {
        if (idValidation(id) && likes.get(id).contains(userId)) {
            Film film = films.get(id);
            likes.get(id).remove((Integer) userId);
            log.debug("Фильму {} удалили лайк.", film);
        } else {
            log.warn("Ошибка при удалении лайка фильму.");
            throw new NotFoundException("Ошибка удаления лайка, проверьте корректность данных.");
        }
    }

    @Override
    public List<Film> getMostPopularFilms(int count) {
        if (count > 0) {
            List<Integer> likesSorted = likes.entrySet()
                    .stream()
                    .sorted(Comparator.comparingInt(e -> e.getValue().size()))
                    .limit(count)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            List<Film> sortedFilms = new ArrayList<>();
            for (Integer v : likesSorted) {
                sortedFilms.add(films.get(v));
            }
            return sortedFilms;

        } else {
            log.warn("Ошибка запроса списка популярных фильмов.");
            throw new ValidationException("Ошибка запроса списка популярных фильмов, проверьте корректность данных.");
        }
    }

    private void addNewId(Film film) {
        int id = filmsIdCount + 1;
        while (films.containsKey(id)) {
            id += id;
        }
        film.setFilmId(id);
        filmsIdCount = id;
    }

    private boolean idValidation(@NonNull int id) {
        return films.containsKey(id);
    }
}
