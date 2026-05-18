package com.PearlSkin.dao;

import com.PearlSkin.entity.User;

public interface UserDao {
    boolean addUser(User user);
    User findByName(String Name);
    User findByEmail(String email);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
