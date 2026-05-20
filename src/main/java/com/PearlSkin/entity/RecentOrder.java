package com.PearlSkin.entity;

public class RecentOrder {

    private int orderId;
    private String customerName;
    private String productName;
    private double amount;

    public RecentOrder(int orderId, String customerName, String productName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmount() {
        return amount;
    }
}
