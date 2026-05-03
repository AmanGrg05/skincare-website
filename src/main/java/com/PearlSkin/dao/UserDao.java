package com.PearlSkin.dao;

import com.PearlSkin.entity.User;

public interface UserDao {
    boolean addUser(User user);
    User findByUsername(String username);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
