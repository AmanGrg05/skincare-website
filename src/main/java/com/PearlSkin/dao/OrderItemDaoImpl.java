package com.PearlSkin.dao;

import com.PearlSkin.entity.RecentOrder;
import com.PearlSkin.utils.DatabaseConnection;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDaoImpl implements OrderItemDao {

    public double getTotalRevenue() {

        double total = 0;
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT SUM(subtotal) FROM orderitems";
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

            String sql = "SELECT COUNT(DISTINCT orderId) FROM orderitems";
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
                SELECT o.orderId,
                       u.name,
                       p.productName,
                       oi.subtotal
                FROM orders o
                JOIN users u ON o.userId = u.userId
                JOIN orderitems oi ON o.orderId = oi.orderId
                JOIN products p ON oi.productId = p.productId
                ORDER BY o.orderId DESC
                LIMIT ?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                RecentOrder order = new RecentOrder(
                        rs.getInt("orderId"),
                        rs.getString("name"),
                        rs.getString("productName"),
                        rs.getDouble("subtotal")
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
