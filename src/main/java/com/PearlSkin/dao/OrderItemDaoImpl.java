package com.PearlSkin.dao;

import com.PearlSkin.entity.RecentOrder;
import com.PearlSkin.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class OrderItemDaoImpl implements OrderItemDao {

    /** Total revenue = sum of all orderitems subtotals. */
    @Override
    public double getTotalRevenue() {
        double total = 0;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT COALESCE(SUM(subtotal), 0) FROM orderitems");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getDouble(1);
        } catch (SQLException e) {
            System.out.println("getTotalRevenue ERROR: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return total;
    }

    /** Total orders = count of rows in orders table. */
    @Override
    public int getTotalOrders() {
        int count = 0;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM orders");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("getTotalOrders ERROR: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return count;
    }

    /** Most recent N order lines, joined to user and product names. */
    @Override
    public ArrayList<RecentOrder> getRecentOrders(int limit) {
        ArrayList<RecentOrder> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql =
                    "SELECT o.orderId, u.name, p.productName, oi.subtotal " +
                            "FROM orders o " +
                            "JOIN users u        ON o.userId     = u.userId " +
                            "JOIN orderitems oi  ON o.orderId    = oi.orderId " +
                            "JOIN products p     ON oi.productId = p.productId " +
                            "ORDER BY o.orderId DESC " +
                            "LIMIT ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RecentOrder(
                        rs.getInt("orderId"),
                        rs.getString("name"),
                        rs.getString("productName"),
                        rs.getDouble("subtotal")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getRecentOrders ERROR: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }
}