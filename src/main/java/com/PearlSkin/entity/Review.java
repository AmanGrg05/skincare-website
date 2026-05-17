package com.PearlSkin.entity;

import java.sql.Timestamp;

public class Review {

    private int reviewId;
    private int userId;
    private int productId;
    private int rating;
    private String comment;
    private Timestamp reviewDate;

    // Constructor for creating new review
    public Review(int userId, int productId,
                  int rating, String comment) {

        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
    }

    // Full constructor
    public Review(int reviewId, int userId, int productId,
                  int rating, String comment,
                  Timestamp reviewDate) {

        this.reviewId = reviewId;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    // Getters
    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    // Setters
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "[Review ID: " + reviewId +
                ", User ID: " + userId +
                ", Product ID: " + productId +
                ", Rating: " + rating +
                ", Comment: " + comment +
                ", Date: " + reviewDate + "]";
    }
}