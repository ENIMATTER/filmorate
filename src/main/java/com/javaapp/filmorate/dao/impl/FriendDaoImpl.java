package com.javaapp.filmorate.dao.impl;

import com.javaapp.filmorate.dao.FriendDao;
import com.javaapp.filmorate.exeption.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FriendDaoImpl implements FriendDao {
    private static final String ADD_FRIEND = "INSERT INTO friends (user_id, friend_id) VALUES (?, ?)";
    private static final String DELETE_FRIEND = "DELETE FROM friends WHERE user_id = ? AND friend_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addFriend(int id, int friendId) {
        try {
            jdbcTemplate.update(ADD_FRIEND, id, friendId);
        } catch (Exception e) {
            throw new NotFoundException("Ошибка запроса, проверьте корректность данных.");
        }
    }

    @Override
    public void deleteFriend(int id, int friendId) {
        try {
            jdbcTemplate.update(DELETE_FRIEND, id, friendId);
        } catch (Exception e) {
            throw new NotFoundException("Ошибка запроса, проверьте корректность данных.");
        }
    }
}
