package com.javaapp.filmorate.dao;

public interface FriendDao {
    void addFriend(int id, int friendId);
    void deleteFriend(int id, int friendId);
}
