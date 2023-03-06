package com.javaapp.filmorate.dao;

import com.javaapp.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    List<User> listUsers();

    User getUserById(int id);

    User createUser(User user);

    User updateUser(User user);

    void addFriend(int id, int friendId);

    void deleteFriend(int id, int friendId);

    List<User> getUserFriends(int id);

    List<User> getCommonFriendList(int id, int friendId);
}
