// ================================
// OrderDaoImpl.java
// ================================

package com.PearlSkin.dao;

import com.PearlSkin.entity.Order;
import com.PearlSkin.entity.OrderItem;
import com.PearlSkin.utils.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public int createOrder(Order order) {

        Connection conn = null;

        try {

            conn =
                    DatabaseConnection.getConnection();

            String sql = """
                    INSERT INTO orders
                    (userId, totalAmount)
                    VALUES (?, ?)
                    """;

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );

            ps.setInt(1, order.getUserId());
            ps.setBigDecimal(2,
                    order.getTotalAmount());

            ps.executeUpdate();

            ResultSet rs =
                    ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error creating order: "
                            + e.getMessage());

        } finally {

            DatabaseConnection.closeConnection(conn);
        }

        return -1;
    }

    @Override
    public boolean insertOrderItems(
            List<OrderItem> items,
            int orderId) {

        Connection conn = null;

        try {

            conn =
                    DatabaseConnection.getConnection();

            String sql = """
                    INSERT INTO orderitems
                    (orderId, productId,
                     quantity, unitPrice,
                     subtotal)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            for (OrderItem item : items) {

                ps.setInt(1, orderId);
                ps.setInt(2,
                        item.getProductId());
                ps.setInt(3,
                        item.getQuantity());
                ps.setBigDecimal(4,
                        item.getUnitPrice());
                ps.setBigDecimal(5,
                        item.getSubtotal());

                ps.addBatch();
            }

            ps.executeBatch();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error inserting order items: "
                            + e.getMessage());

        } finally {

            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }
}