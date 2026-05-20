package com.PearlSkin.dao;

import com.PearlSkin.utils.DatabaseConnection;
import com.PearlSkin.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public boolean insertProduct (Product product){

        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO Product " +
                    "(productName, description, price, stockQuantity, " +
                    "categoryName, expiryDate, ingredients, skinConcern, Image)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getStockQuantity());
            ps.setString(5, product.getCategoryName());
            ps.setDate(6, product.getExpiryDate());
            ps.setString(7, product.getIngredients());
            ps.setString(8, product.getSkinConcern());
            ps.setString(9, product.getImage());
            ps.executeUpdate();
            return true;

        } catch (SQLException e){
                System.out.println("Error in inserting product" + e.getMessage());
            } finally {
            DatabaseConnection.closeConnection(conn);
        }
            return false;
        }

    @Override
        public ArrayList<Product> getAllProducts() {
            ArrayList<Product> products = new ArrayList<>();
            Connection conn = null;
            try {
                conn = DatabaseConnection.getConnection();
                String sql = "SELECT * FROM Product";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("productId"),
                            rs.getString("categoryName"),
                            rs.getString("productName"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stockQuantity"),
                            rs.getString("skinConcern"),
                            rs.getString("ingredients"),
                            rs.getDate("expiryDate"),
                            rs.getString("description"),
                            rs.getString("Image")
                    );
                    products.add(product);
                }
            } catch (SQLException e) {
                System.out.println("Error in getting all products" + e.getMessage());
            } finally {
                DatabaseConnection.closeConnection(conn);
            }
            return products;
        }

    @Override
        public boolean updateProduct (Product product) {
            Connection conn = null;

            try {
                conn = DatabaseConnection.getConnection();
                String sql = "UPDATE Product SET productName = ?, description = ?, " +
                        "price = ?, stockQuantity = ? WHERE productID = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getDescription());
                ps.setBigDecimal(3, product.getPrice());
                ps.setInt(4, product.getStockQuantity());
                ps.setInt(5, product.getProductId());
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Error in updating product" + e.getMessage());
                return false;
            } finally {
                DatabaseConnection.closeConnection(conn);
            }
        }

    @Override
        public boolean deleteProduct (int id) {
            Connection conn = null;

            try {
                conn = DatabaseConnection.getConnection();
                String sql = "DELETE FROM Product WHERE productId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Error in deleting product" + e.getMessage());
                return false;
            } finally {
                DatabaseConnection.closeConnection(conn);
            }
        }

    @Override
        public Product getProductById(int id) {
            Connection conn = null;

            try {
                conn = DatabaseConnection.getConnection();
                String sql = "SELECT * FROM Product WHERE productId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return new Product(
                            rs.getInt("productID"),
                            rs.getString("categoryName"),
                            rs.getString("productName"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stockQuantity"),
                            rs.getString("skinConcern"),
                            rs.getString("ingredients"),
                            rs.getDate("expiryDate"),
                            rs.getString("description"),
                            rs.getString("Image")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error in getting product" + e.getMessage());
            } finally {
                DatabaseConnection.closeConnection(conn);
            }
            return null;
        }
    @Override
        public ArrayList<Product> getProductsByName(String name) {
            ArrayList<Product> products = new ArrayList<>();
            Connection conn = null;

            try {
                conn = DatabaseConnection.getConnection();
                String sql = "SELECT * FROM Product WHERE productName LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + name +"%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("productID"),
                            rs.getString("categoryName"),
                            rs.getString("productName"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stockQuantity"),
                            rs.getString("skinConcern"),
                            rs.getString("ingredients"),
                            rs.getDate("expiryDate"),
                            rs.getString("description"),
                            rs.getString("Image")
                    );
                    products.add(product);
                }
            } catch (SQLException e) {
                System.out.println("Error in getting all products" + e.getMessage());
            } finally {
                DatabaseConnection.closeConnection(conn);
            }
            return products;
        }

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
                        rs.getInt("productID"),
                        rs.getString("categoryName"),
                        rs.getString("productName"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stockQuantity"),
                        rs.getString("skinConcern"),
                        rs.getString("ingredients"),
                        rs.getDate("expiryDate"),
                        rs.getString("description"),
                        rs.getString("Image")
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
                SELECT p.productName,
                       SUM(oi.quantity) AS total_sold,
                       SUM(oi.quantity * oi.unitPrice) AS revenue
                FROM Product p
                JOIN orderItems oi ON p.productID = oi.productID
                GROUP BY p.productID, p.productName
                ORDER BY total_sold DESC
                LIMIT ?
            """;

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, limit);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                TopProduct product = new TopProduct(
                        rs.getString("productName"),
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

    @Override
    public boolean reduceStock(int productId, int stockQuantity) {
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE Product SET stockQuantity = stockQuantity - ? WHERE productID = ? AND stockQuantity >= ?";
            PreparedStatement ps = conn.prepareStatement(sql);{
                ps.setInt(1, stockQuantity);
                ps.setInt(2, productId);
                ps.setInt(3, stockQuantity);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e){
            System.out.println("Error reducing stock: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
        }
}

