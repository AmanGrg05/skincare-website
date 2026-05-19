package com.PearlSkin.dao;

import com.PearlSkin.entity.Product;

import java.sql.PreparedStatement;
import com.PearlSkin.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao {
    @Override
    public ArrayList<Product> getFeaturedProducts(){
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM Product";

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("CategoryName"),
                        rs.getString("ProductName"),
                        rs.getString("Brand"),
                        rs.getBigDecimal("Price"),
                        rs.getInt("StockQuantity"),
                        rs.getString("SkinConcern"),
                        rs.getString("Ingredients"),
                        rs.getDate("ExpiryDate"),
                        rs.getString("Description")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return products;
    }
    }

