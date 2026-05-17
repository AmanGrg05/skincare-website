package com.PearlSkin.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {

    private int orderId;
    private int userId;
    private Timestamp orderDate;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String shippingAddress;

    // Constructor for creating new order
    public Order(int userId, BigDecimal totalAmount,
                 String orderStatus, String shippingAddress) {

        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
    }

    // Full constructor
    public Order(int orderId, int userId, Timestamp orderDate,
                 BigDecimal totalAmount, String orderStatus,
                 String shippingAddress) {

        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    // Setters
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "[Order ID: " + orderId +
                ", User ID: " + userId +
                ", Total: " + totalAmount +
                ", Status: " + orderStatus +
                ", Date: " + orderDate + "]";
    }
}