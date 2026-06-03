package com.PearlSkin.dao;

import com.PearlSkin.entity.Order;
import com.PearlSkin.entity.OrderItem;
import com.PearlSkin.entity.RecentOrder;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao {

    int createOrder(Order order);
    ArrayList<RecentOrder> getAllOrders();
    boolean insertOrderItems(List<OrderItem> items, int orderId);

}