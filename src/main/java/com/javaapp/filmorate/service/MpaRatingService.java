package com.javaapp.filmorate.service;

import com.javaapp.filmorate.dao.MpaRatingDao;
import com.javaapp.filmorate.exeption.NotFoundException;
import com.javaapp.filmorate.model.MpaRating;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MpaRatingService {
    private final MpaRatingDao mpaRatingDao;

    public List<MpaRating> getMpaRatings() {
        return mpaRatingDao.getMpaRatings();
    }

    public MpaRating getMpaRatingById(int id) {
        try {
            return mpaRatingDao.getMpaRatingById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Ошибка запроса рейтинга.");
            throw new NotFoundException("Ошибка запроса рейтинга, проверьте корректность данных.");
        }
    }
}
