package com.PearlSkin.entity;

import java.sql.Timestamp;

public class User {

    private int userId;
    private String name;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private String skinType;
    private Timestamp registrationDate;

    // Constructor for creating new user
    public User(String name, String email,
                String passwordHash, String phoneNumber,
                String address, String skinType) {

        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.skinType = skinType;
    }

    // Full constructor
    public User(int userId, String name, String email,
                String passwordHash, String phoneNumber,
                String address, String skinType,
                Timestamp registrationDate) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.skinType = skinType;
        this.registrationDate = registrationDate;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getSkinType() {
        return skinType;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    @Override
    public String toString() {
        return "[User ID: " + userId +
                ", Name: " + name +
                ", Email: " + email +
                ", Skin Type: " + skinType +
                ", Registered: " + registrationDate + "]";
    }
}