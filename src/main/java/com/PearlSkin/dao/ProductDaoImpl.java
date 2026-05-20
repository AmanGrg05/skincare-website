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
            String sql = "INSERT INTO products (Productname,description,productId, price, Stock) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,  product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getCategoryName());
            ps.setString(4, product.getDescription());
            ps.setBigDecimal(5, product.getPrice());
            ps.setInt(6, product.getStockQuantity());
            ps.setString(7, product.getBrand());
            ps.setDate(8, product.getExpiryDate());
            ps.setString(9, product.getIngredients());
            ps.setString(10, product.getSkinConcern());
            ps.executeUpdate();
            return true;

        } catch (SQLException e){
                System.out.println("Error in inserting product" + e.getMessage());
            }
            return false;
        }

    @Override
        public ArrayList<Product> getAllProducts() {
            ArrayList<Product> products = new ArrayList<>();
            Connection conn = null;
            try {
                conn = DatabaseConnection.getConnection();
                String sql = "SELECT * FROM products";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("productId"),
                            rs.getString("categoryName"),
                            rs.getString("productName"),
                            rs.getString("brand"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stockQuantity"),
                            rs.getString("skinConcern"),
                            rs.getString("ingredients"),
                            rs.getDate("expiryDate"),
                            rs.getString("description")
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
                String sql = "UPDATE product SET ProductName = ?, Description = ?," +
                        " Price = ?, Stock = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getDescription());
                ps.setBigDecimal(3, product.getPrice());
                ps.setInt(4, product.getStockQuantity());
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
                String sql = "DELETE FROM product WHERE id = ?";
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
                String sql = "SELECT * FROM product WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Product product = new Product(
                            rs.getInt("productId"),
                            rs.getString("categoryName"),
                            rs.getString("productName"),
                            rs.getString("brand"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stockQuantity"),
                            rs.getString("skinConcern"),
                            rs.getString("ingredients"),
                            rs.getDate("expiryDate"),
                            rs.getString("description")
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
                String sql = "SELECT * FROM product WHERE ProductName LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + name +"%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("productId"),
                            rs.getString("categoryName"),
                            rs.getString("productName"),
                            rs.getString("brand"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stockQuantity"),
                            rs.getString("skinConcern"),
                            rs.getString("ingredients"),
                            rs.getDate("expiryDate"),
                            rs.getString("description")
                    );
                    products.add(product);
                }
            } catch (SQLException e) {
                System.out.println("Error in getting all products" + e.getMessage());
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

