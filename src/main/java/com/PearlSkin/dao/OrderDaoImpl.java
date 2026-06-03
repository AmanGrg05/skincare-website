package com.PearlSkin.dao;

import com.PearlSkin.entity.Order;
import com.PearlSkin.entity.OrderItem;
import com.PearlSkin.utils.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    /**
     * Inserts the order header and all its items in a single transaction
     * on one shared Connection — the same pattern UserDaoImpl uses.
     *
     * Root cause of the original bug: the old code opened two separate
     * connections (one in createOrder, one in insertOrderItems). If the
     * second connection hit the UNIQUE KEY violation on (orderId, productId)
     * it threw a silent SQLException, returned false, but CartServlet never
     * checked that return value so the cart was cleared as if it succeeded.
     *
     * @return new orderId on success, -1 on any failure.
     */
    @Override

    public int createOrder(Order order) {

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String sql =
                    "INSERT INTO orders (userId, totalAmount, shippingAddress, orderStatus) " +
                            "VALUES (?, ?, ?, ?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getUserId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, order.getShippingAddress());
            ps.setString(4, "PENDING");

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (!rs.next()) {
                conn.rollback();
                return -1;
            }

            int orderId = rs.getInt(1);

            String itemSql =
                    "INSERT INTO orderitems (orderId, productId, quantity, unitPrice, subtotal) " +
                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement itemPs = conn.prepareStatement(itemSql);

            for (OrderItem item : order.getItems()) {

                itemPs.setInt(1, orderId);
                itemPs.setInt(2, item.getProductId());
                itemPs.setInt(3, item.getQuantity());
                itemPs.setBigDecimal(4, item.getUnitPrice());
                itemPs.setBigDecimal(5, item.getSubtotal());

                itemPs.addBatch();
            }

            itemPs.executeBatch();
            conn.commit();

            return orderId;

        } catch (Exception e) {
            e.printStackTrace();

            try {
                if (conn != null) conn.rollback();
            } catch (Exception ignored) {}

            return -1;

        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (Exception ignored) {}

            DatabaseConnection.closeConnection(conn);
        }
    }

    /**
     * Not used — createOrder handles item inserts inside its own transaction.
     * Retained only to satisfy the OrderDao interface.
     */
    @Override
    public boolean insertOrderItems(List<OrderItem> items, int orderId) {
        return false;
    }
}