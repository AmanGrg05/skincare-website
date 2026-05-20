package com.PearlSkin.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Product {

    private int productId;
    private String categoryName;
    private String productName;
    private BigDecimal price;
    private int stockQuantity;
    private String skinConcern;
    private String ingredients;
    private Date expiryDate;
    private String description;
    private String image;

    // Constructor for creating new product
    public Product(String categoryName, String productName,
                   BigDecimal price,
                   int stockQuantity, String skinConcern,
                   String ingredients, Date expiryDate,
                   String description,  String image) {

        this.categoryName = categoryName;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.skinConcern = skinConcern;
        this.ingredients = ingredients;
        this.expiryDate = expiryDate;
        this.description = description;
        this.image = image;
    }

    // Full constructor
    public Product(int productId, String categoryName,
                   String productName, BigDecimal price, int stockQuantity,
                   String skinConcern, String ingredients,
                   Date expiryDate, String description,  String image) {

        this.productId = productId;
        this.categoryName = categoryName;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.skinConcern = skinConcern;
        this.ingredients = ingredients;
        this.expiryDate = expiryDate;
        this.description = description;
        this.image = image;
    }

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getSkinConcern() {
        return skinConcern;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    // Setters

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setSkinConcern(String skinConcern) {
        this.skinConcern = skinConcern;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "[Product ID: " + productId +
                ", Name: " + productName +
                ", Category: " + categoryName +
                ", Price: " + price +
                ", Stock: " + stockQuantity + "]";
    }
}