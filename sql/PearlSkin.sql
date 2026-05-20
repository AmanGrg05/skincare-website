-- ==========================================
-- Create and Use Database
-- ==========================================
CREATE DATABASE IF NOT EXISTS PearlSkin;
USE PearlSkin;

-- ==========================================
-- Drop existing tables (FK order)
-- ==========================================
DROP TABLE IF EXISTS Review;
DROP TABLE IF EXISTS OrderItem;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS User;

-- ==========================================
-- USER TABLE
-- ==========================================
CREATE TABLE User (
                      UserID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      Name VARCHAR(100) NOT NULL,
                      Email VARCHAR(100) NOT NULL UNIQUE,
                      PasswordHash VARCHAR(255) NOT NULL,
                      PhoneNumber VARCHAR(20),
                      Address TEXT,
                      SkinType VARCHAR(50),
                      RegistrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================
-- CATEGORY TABLE
-- CategoryName as PRIMARY KEY
-- ==========================================
CREATE TABLE Category (
                          CategoryName VARCHAR(100) PRIMARY KEY,
                          Description TEXT
);

-- ==========================================
-- PRODUCT TABLE
-- FK now references CategoryName
-- ==========================================
CREATE TABLE Product (
                         ProductID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         CategoryName VARCHAR(100) NOT NULL,
                         ProductName VARCHAR(150) NOT NULL,
                         Brand VARCHAR(100),
                         Price DECIMAL(10,2) NOT NULL,
                         StockQuantity INT DEFAULT 0,
                         SkinConcern VARCHAR(100),
                         Ingredients TEXT,
                         ExpiryDate DATE,
                         Description TEXT,

                         FOREIGN KEY (CategoryName) REFERENCES Category(CategoryName)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
);

-- ==========================================
-- ORDERS TABLE
-- ==========================================
CREATE TABLE Orders (
                        OrderID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        UserID INT NOT NULL,
                        OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        TotalAmount DECIMAL(10,2),
                        OrderStatus VARCHAR(50) DEFAULT 'Pending',
                        ShippingAddress TEXT,

                        FOREIGN KEY (UserID) REFERENCES User(UserID)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);

-- ==========================================
-- ORDERITEM TABLE
-- ==========================================
CREATE TABLE OrderItem (
                           OrderItemID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           OrderID INT NOT NULL,
                           ProductID INT NOT NULL,
                           Quantity INT NOT NULL,
                           UnitPrice DECIMAL(10,2) NOT NULL,
                           Subtotal DECIMAL(10,2),

                           FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
                               ON DELETE CASCADE,

                           FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
                               ON DELETE CASCADE,

                           UNIQUE (OrderID, ProductID)
);

-- ==========================================
-- REVIEW TABLE
-- ==========================================
CREATE TABLE Review (
                        ReviewID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        UserID INT NOT NULL,
                        ProductID INT NOT NULL,
                        Rating INT CHECK (Rating BETWEEN 1 AND 5),
                        Comment TEXT,
                        ReviewDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                        FOREIGN KEY (UserID) REFERENCES User(UserID)
                            ON DELETE CASCADE,

                        FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
                            ON DELETE CASCADE
);
ALTER TABLE Product ADD Image VARCHAR(255);

INSERT INTO Category (CategoryName, Description) VALUES
                                                     ('Serum','Serums'),
                                                     ('Eye Care','Eye products'),
                                                     ('Facial Mist','Mists'),
                                                     ('Moisturizer','Moisturizers'),
                                                     ('Cleanser','Cleansers'),
                                                     ('Sunscreen','Sunscreens');
INSERT INTO Product
(ProductName, Description, Price, StockQuantity, CategoryName, Brand, ExpiryDate, Ingredients, SkinConcern, Image)
VALUES
    (
        'PearlSkin Radiance Serum',
        'Lightweight brightening serum that deeply hydrates and enhances natural glow.',
        1299.99,
        50,
        'Serum',
        'PearlSkin',
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
        'PearlSkin',
        '2027-10-15',
        'Pearl Extract, Peptides, Niacinamide',
        'Dark Circles',
        'eye-cream.png'
    ),
    (
        'PearlSkin Dew Mist',
        'Refreshing facial mist that hydrates and revives tired skin instantly.',
        899.99,
        60,
        'Facial Mist',
        'PearlSkin',
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
        'PearlSkin',
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
        'PearlSkin',
        '2027-07-18',
        'Chamomile Extract, Aloe Vera, Pearl Extract',
        'Sensitive Skin',
        'cleanser.png'
    );

