package com.PearlSkin.dao;

import com.PearlSkin.entity.Order;
import com.PearlSkin.entity.OrderItem;

import java.util.List;

public interface OrderDao {

    int createOrder(Order order);

    boolean insertOrderItems(List<OrderItem> items, int orderId);
}