-- ==========================================
-- Create and Use Database
-- ==========================================
CREATE DATABASE IF NOT EXISTS PearlSkin;
USE PearlSkin;

-- ==========================================
-- Drop existing tables (FK order)
-- ==========================================

DROP TABLE IF EXISTS orderItems;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

-- ==========================================
-- USER TABLE
-- ==========================================
CREATE TABLE users (
                    userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    passwordHash VARCHAR(255) NOT NULL,
                    phoneNumber VARCHAR(20),
                    address TEXT,
                    skinType VARCHAR(50),
                    registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    isAdmin BOOLEAN DEFAULT FALSE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ==========================================
-- CATEGORY TABLE
-- CategoryName as PRIMARY KEY
-- ==========================================
CREATE TABLE categories (
                        categoryName VARCHAR(100) PRIMARY KEY,
                        description TEXT,
                        created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ==========================================
-- PRODUCT TABLE
-- FK now references CategoryName
-- ==========================================
CREATE TABLE products (
                         productId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         categoryName VARCHAR(100) NOT NULL,
                         productName VARCHAR(150) NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         stockQuantity INT DEFAULT 0,
                         skinConcern VARCHAR(100),
                         ingredients TEXT,
                         expiryDate DATE,
                         description TEXT,
                         Image VARCHAR(255),
                         created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                         FOREIGN KEY (categoryName) REFERENCES category(categoryName)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
);

-- ==========================================
-- ORDERS TABLE
-- ==========================================
CREATE TABLE orders (
                        orderId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        userId INT NOT NULL,
                        orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        totalAmount DECIMAL(10,2),
                        shippingAddress TEXT NOT NULL,
                        orderStatus VARCHAR(50) DEFAULT 'PENDING',
                        created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (userId) REFERENCES users(userId)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);

-- ==========================================
-- ORDERITEM TABLE
-- ==========================================
CREATE TABLE orderItems (
                           orderItemId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           orderId INT NOT NULL,
                           productId INT NOT NULL,
                           quantity INT NOT NULL,
                           unitPrice DECIMAL(10,2) NOT NULL,
                           subtotal DECIMAL(10,2),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (orderId) REFERENCES orders(orderId)
                               ON DELETE CASCADE,

                           FOREIGN KEY (productId) REFERENCES Product(productId)
                               ON DELETE CASCADE,

                           UNIQUE (orderId, productId)
);




