    package com.PearlSkin.entity;

    import java.sql.Timestamp;

    public class User {

        private int userId;
        private String name;
        private String email;
        private String password;
        private String address;
        private String skinType;
        private Timestamp registrationDate;

        // Constructor for creating new user
        public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;

        }

        // Full constructor
        public User(int userId, String name, String email,
                    String password, String address, String skinType,
                    Timestamp registrationDate) {

            this.userId = userId;
            this.name = name;
            this.email = email;
            this.password = password;
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

        public String getPassword() {
            return password;
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

        public void setPassword(String password) {
            this.password = password;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setSkinType(String skinType) {
            this.skinType = skinType;
        }

        @Override
        public String toString() {
            return "(User ID: " + userId +
                    ", Name: " + name +
                    ", Email: " + email + ")";
        }
    }