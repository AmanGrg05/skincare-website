package com.PearlSkin.dao;

import com.PearlSkin.entity.Product;

import java.sql.PreparedStatement;

import com.PearlSkin.entity.TopProduct;
import com.PearlSkin.utils.DatabaseConnection;
import java.sql.*;
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

    @Override
    public int countProducts() {

        int count = 0;
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM Product";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error count products: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return count;
    }

    @Override
    public ArrayList<TopProduct> getTopProducts(int limit) {

        ArrayList<TopProduct> topProducts = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = """
                SELECT p.ProductName,
                       SUM(oi.Quantity) AS total_sold,
                       SUM(oi.Quantity * oi.Price) AS revenue
                FROM Product p
                JOIN OrderItem oi ON p.ProductID = oi.ProductID
                GROUP BY p.ProductID, p.ProductName
                ORDER BY total_sold DESC
                LIMIT ?
            """;

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, limit);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                TopProduct product = new TopProduct(
                        rs.getString("ProductName"),
                        rs.getInt("total_sold"),
                        rs.getDouble("revenue")
                );

                topProducts.add(product);
            }

        } catch (SQLException e) {
            System.out.println("Error top products: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return topProducts;
    }

}

