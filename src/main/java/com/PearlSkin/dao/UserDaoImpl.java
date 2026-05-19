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
            String sql = "INSERT INTO users (Name, Email, PasswordHash, PhoneNumber) VALUES (?, ?, ?, ?)";
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
                        rs.getInt("UserId"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("PasswordHash"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("SkinType"),
                        rs.getTimestamp("RegistrationDate"),
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
                        rs.getInt("UserId"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("PasswordHash"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("SkinType"),
                        rs.getTimestamp("RegistrationDate"),
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
            String sql = "UPDATE users SET Name = ?, Email = ?, PasswordHash = ?, PhoneNumber = ? WHERE UserId = ?";
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
            String sql = "DELETE FROM users WHERE UserId = ?";
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
}