package com.PearlSkin.dao;

import com.PearlSkin.entity.User;
import com.PearlSkin.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        if (findByUsername(user.getUsername()) != null) {
            System.out.println("Username already exists: " + user.getUsername());
            return false;
        }
        if (findByEmail(user.getEmail()) != null) {
            System.out.println("Email already exits: " + user.getEmail());
            return false;
        }

        Connection con = null;
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }finally{
            DatabaseConnection.closeConnection(conn);
        }
    }
}
