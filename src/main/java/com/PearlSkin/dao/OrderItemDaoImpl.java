package com.PearlSkin.dao;

import com.PearlSkin.entity.RecentOrder;
import com.PearlSkin.utils.DatabaseConnection;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDaoImpl {

    public double getTotalRevenue() {

        double total = 0;
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT SUM(TotalAmount) FROM OrderItem";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println("Error revenue: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return total;
    }


    public int getTotalOrders() {

        int count = 0;
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT COUNT(DISTINCT OrderID) FROM OrderItem";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error orders: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return count;
    }

    public ArrayList<RecentOrder> getRecentOrders(int limit) {

        ArrayList<RecentOrder> recentOrders = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = """
                SELECT o.OrderID,
                       u.FullName,
                       p.ProductName,
                       oi.TotalAmount
                FROM Orders o
                JOIN User u ON o.UserID = u.UserID
                JOIN OrderItem oi ON o.OrderID = oi.OrderID
                JOIN Product p ON oi.ProductID = p.ProductID
                ORDER BY o.OrderID DESC
                LIMIT ?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                RecentOrder order = new RecentOrder(
                        rs.getInt("OrderID"),
                        rs.getString("FullName"),
                        rs.getString("ProductName"),
                        rs.getDouble("TotalAmount")
                );

                recentOrders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("Error recent orders: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return recentOrders;
    }
}
