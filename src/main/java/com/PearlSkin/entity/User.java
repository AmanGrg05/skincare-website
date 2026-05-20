    package com.PearlSkin.entity;

    import java.sql.Timestamp;

    public class User {

        private int UserId;
        private String Name;
        private String Email;
        private String PasswordHash;
        private String PhoneNumber;
        private String Address;
        private String SkinType;
        private Timestamp RegistrationDate;
        private boolean isAdmin;

        // Constructor for creating new user
        public User(String Name, String Email, String PasswordHash, String PhoneNumber) {
            this.Name = Name;
            this.Email = Email;
            this.PasswordHash = PasswordHash;
            this.PhoneNumber = PhoneNumber;

        }

        // Full constructor
        public User(int UserId, String Name, String Email, String PasswordHash, String PhoneNumber,
                    String Address, String SkinType, Timestamp RegistrationDate, boolean isAdmin) {

            this.UserId = UserId;
            this.Name = Name;
            this.Email = Email;
            this.PasswordHash = PasswordHash;
            this.PhoneNumber = PhoneNumber;
            this.Address = Address;
            this.SkinType = SkinType;
            this.RegistrationDate = RegistrationDate;
            this.isAdmin = isAdmin;
        }

        // Getters
        public int getUserId() {
            return UserId;
        }

        public String getName() {
            return Name;
        }

        public String getEmail() {
            return Email;
        }

        public String getPasswordHash() {
            return PasswordHash;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public String getAddress() {
            return Address;
        }

        public String getSkinType() {
            return SkinType;
        }

        public Timestamp getRegistrationDate() {
            return RegistrationDate;
        }
        public boolean isAdmin() {
            return isAdmin;
        }

        // Setters
        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }


        public void setPasswordHash(String PasswordHash) {
            this.PasswordHash = PasswordHash;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public void setSkinType(String SkinType) {
            this.SkinType = SkinType;
        }


        @Override
        public String toString() {
            return "(User ID: " + UserId +
                    ", Name: " + Name +
                    ", Email: " + Email + ")";
        }
    }