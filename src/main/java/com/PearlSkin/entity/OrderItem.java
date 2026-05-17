package com.PearlSkin.entity;

import java.math.BigDecimal;

public class OrderItem {

    private int orderItemId;
    private int orderId;
    private int productId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    // Constructor for creating new order item
    public OrderItem(int orderId, int productId,
                     int quantity, BigDecimal unitPrice) {

        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    // Full constructor
    public OrderItem(int orderItemId, int orderId, int productId,
                     int quantity, BigDecimal unitPrice,
                     BigDecimal subtotal) {

        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    // Getters
    public int getOrderItemId() {
        return orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    // Setters
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = this.unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }

    @Override
    public String toString() {
        return "[OrderItem ID: " + orderItemId +
                ", Order ID: " + orderId +
                ", Product ID: " + productId +
                ", Quantity: " + quantity +
                ", Unit Price: " + unitPrice +
                ", Subtotal: " + subtotal + "]";
    }
}