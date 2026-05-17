package com.PearlSkin.entity;

public class Category {

    private String categoryName;
    private String description;

    // Constructor for creating new category
    public Category(String categoryName, String description) {

        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters
    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[Category Name: " + categoryName +
                ", Description: " + description + "]";
    }
}