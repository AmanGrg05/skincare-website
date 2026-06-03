package com.PearlSkin.dao;


import com.PearlSkin.utils.DatabaseConnection;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.PearlSkin.entity.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        if (findByName(user.getName()) != null) {
            System.out.println("Username already exists: " + user.getName());
            return false;
        }
        if (findByEmail(user.getEmail()) != null) {
            System.out.println("Email already exists: " + user.getEmail());
            return false;
        }

        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO users (name, email, passwordHash, phoneNumber) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getPhoneNumber());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }finally{
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public User findByName(String name) {
        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE LOWER(name) = LOWER(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new User(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("passwordHash"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("skinType"),
                        rs.getTimestamp("registrationDate"),
                        rs.getBoolean("isAdmin")
                );
            }
        }catch (SQLException e) {
            System.out.println("Error finding user by name: " + e.getMessage());
        }finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new User(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("passwordHash"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("skinType"),
                        rs.getTimestamp("registrationDate"),
                        rs.getBoolean("isAdmin")
                );
            }
        }catch (SQLException e) {
            System.out.println("Error finding user by email: " + e.getMessage());
        }finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE users SET name = ?, email = ?, passwordHash = ?, phoneNumber = ? WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getPhoneNumber());
            statement.setInt(5, user.getUserId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }else{
                return false;
            }
        }catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }finally{
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM users WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate(); //Checks whether the operation actually worked
            return rowsDeleted > 0;    //if one row is affected returns 1 and if no row is affected returns 0
        }catch (SQLException e){
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }finally{
            DatabaseConnection.closeConnection(conn);
        }
    }


    public int countCustomers() {

        int count = 0;
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM users WHERE isAdmin = FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error customers: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return count;
    }
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("passwordHash"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("skinType"),
                        rs.getTimestamp("registrationDate"),
                        rs.getBoolean("isAdmin")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return users;
    }
    public List<User> searchUsers(String keyword) {

        List<User> users = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM users " +
                    "WHERE LOWER(name) LIKE ? OR LOWER(email) LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            String searchValue = "%" + keyword.toLowerCase() + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("passwordHash"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("skinType"),
                        rs.getTimestamp("registrationDate"),
                        rs.getBoolean("isAdmin")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error searching users: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return users;
    }
}

