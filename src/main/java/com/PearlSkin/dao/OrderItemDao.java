package com.PearlSkin.dao;

import com.PearlSkin.entity.RecentOrder;

import java.util.ArrayList;

public interface OrderItemDao {
    double getTotalRevenue();

    int getTotalOrders();

    ArrayList<RecentOrder> getRecentOrders(int limit);
}
