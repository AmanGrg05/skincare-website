-- ==========================================
-- Create and Use Database
-- ==========================================
CREATE DATABASE IF NOT EXISTS PearlSkin;
USE PearlSkin;

-- ==========================================
-- Drop existing tables (FK order)
-- ==========================================
DROP TABLE IF EXISTS reviews;
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
CREATE TABLE category (
                        categoryName VARCHAR(100) PRIMARY KEY,
                        description TEXT,
                        created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ==========================================
-- PRODUCT TABLE
-- FK now references CategoryName
-- ==========================================
CREATE TABLE product (
                         productId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         categoryName VARCHAR(100) NOT NULL,
                         productName VARCHAR(150) NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         stockQuantity INT DEFAULT 0,
                         skinConcern VARCHAR(100),
                         ingredients TEXT,
                         expiryDate DATE,
                         description TEXT,
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

-- ==========================================
-- REVIEW TABLE
-- ==========================================
CREATE TABLE reviews (
                        reviewId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        userId INT NOT NULL,
                        productId INT NOT NULL,
                        rating INT CHECK (rating BETWEEN 1 AND 5),
                        comment TEXT,
                        reviewDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (userId) REFERENCES users(userId)
                            ON DELETE CASCADE,

                        FOREIGN KEY (productId) REFERENCES product(productId)
                            ON DELETE CASCADE

);

ALTER TABLE product ADD Image VARCHAR(255);

INSERT INTO categories (CategoryName, Description) VALUES
                                                     ('Serum','Serums'),
                                                     ('Eye Care','Eye products'),
                                                     ('Facial Mist','Mists'),
                                                     ('Moisturizer','Moisturizers'),
                                                     ('Cleanser','Cleansers'),
                                                     ('Sunscreen','Sunscreens');


INSERT INTO product
(productName, description, price, stockQuantity, categoryName, expiryDate, ingredients, skinConcern, Image)
VALUES
    (
        'PearlSkin Radiance Serum',
        'Lightweight brightening serum that deeply hydrates and enhances natural glow.',
        1299.99,
        50,
        'Serum',
        '2027-12-31',
        'Pearl Extract, Hyaluronic Acid, Niacinamide',
        'Dullness',
        'serum.png'
    ),
    (
        'PearlSkin Silk Eye Cream',
        'Nourishing eye cream that helps reduce puffiness, dark circles, and fine lines.',
        999.99,
        40,
        'Eye Care',
        '2027-10-15',
        'Pearl Extract, Peptides, Niacinamide',
        'Dark Circles',
        'eye cream.png'
    ),
    (
        'PearlSkin Dew Mist',
        'Refreshing facial mist that hydrates and revives tired skin instantly.',
        899.99,
        60,
        'Facial Mist',
        '2027-09-20',
        'Pearl Extract, Rose Water, Hyaluronic Acid',
        'Dryness',
        'mist.png'
    ),
    (
        'PearlSkin Nourishing Glow Moisturizer',
        'Rich moisturizer that hydrates, nourishes, and protects skin all day.',
        1499.99,
        35,
        'Moisturizer',
        '2027-11-30',
        'Shea Butter, Pearl Extract, Vitamin E',
        'Dry Skin',
        'moisturizer.png'
    ),
    (
        'PearlSkin Velvet Cleanser',
        'Gentle cream cleanser that removes impurities without stripping moisture.',
        799.99,
        70,
        'Cleanser',
        '2027-07-18',
        'Chamomile Extract, Aloe Vera, Pearl Extract',
        'Sensitive Skin',
        'cleanser.png'
    ),
    (
        'PearlSkin UV Shield Sunscreen SPF 50',
        'Lightweight broad-spectrum sunscreen that protects skin from UVA/UVB rays while keeping it hydrated and non-greasy.',
        1399.99,
        60,
        'Sunscreen',
        '2027-12-31',
        'Zinc Oxide, Titanium Dioxide, Aloe Vera, Vitamin E',
        'Sun Protection',
        'sunscreen.png'
    );


