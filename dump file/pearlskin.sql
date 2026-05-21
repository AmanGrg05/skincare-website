-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2026 at 08:54 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pearlskin`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `categoryName` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`categoryName`, `description`, `created_at`, `updated_at`) VALUES
('Cleanser', 'Products used to remove dirt and oil from skin', '2026-05-21 02:19:16', '2026-05-21 02:19:16'),
('Eye Care', 'Special care for under-eye area', '2026-05-21 02:19:16', '2026-05-21 02:19:16'),
('Moisturizer', 'Hydration and skin barrier protection', '2026-05-21 02:19:16', '2026-05-21 02:19:16'),
('Serum', 'Active ingredient concentrated treatments', '2026-05-21 02:19:16', '2026-05-21 02:19:16'),
('Sunscreen', 'UV protection skincare products', '2026-05-21 02:19:16', '2026-05-21 02:19:16');

-- --------------------------------------------------------

--
-- Table structure for table `orderitems`
--

CREATE TABLE `orderitems` (
  `orderItemId` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unitPrice` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitems`
--

INSERT INTO `orderitems` (`orderItemId`, `orderId`, `productId`, `quantity`, `unitPrice`, `subtotal`, `created_at`, `updated_at`) VALUES
(10, 7, 1, 2, 12.99, 25.98, '2026-05-21 02:33:15', '2026-05-21 02:33:15'),
(11, 7, 3, 1, 22.00, 22.00, '2026-05-21 02:33:15', '2026-05-21 02:33:15'),
(12, 8, 3, 1, 22.00, 22.00, '2026-05-21 02:33:15', '2026-05-21 02:33:15');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `orderDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `totalAmount` decimal(10,2) DEFAULT NULL,
  `shippingAddress` text NOT NULL,
  `orderStatus` varchar(50) DEFAULT 'PENDING',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `userId`, `orderDate`, `totalAmount`, `shippingAddress`, `orderStatus`, `created_at`, `updated_at`) VALUES
(7, 2, '2026-05-21 02:33:15', 45.49, 'Kathmandu, Nepal', 'PENDING', '2026-05-21 02:33:15', '2026-05-21 02:33:15'),
(8, 3, '2026-05-21 02:33:15', 22.00, 'Lalitpur, Nepal', 'DELIVERED', '2026-05-21 02:33:15', '2026-05-21 02:33:15');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productId` int(11) NOT NULL,
  `categoryName` varchar(100) NOT NULL,
  `productName` varchar(150) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stockQuantity` int(11) DEFAULT 0,
  `skinConcern` varchar(100) DEFAULT NULL,
  `ingredients` text DEFAULT NULL,
  `expiryDate` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`productId`, `categoryName`, `productName`, `price`, `stockQuantity`, `skinConcern`, `ingredients`, `expiryDate`, `description`, `Image`, `created_at`, `updated_at`) VALUES
(1, 'Cleanser', 'Gentle Foaming Cleanser', 12.99, 50, 'Oily Skin', 'Water, Glycerin, Salicylic Acid', '2027-12-01', 'Removes dirt and excess oil without drying skin', '2026-05-21T06-52-07.862880400_cleanser.png', '2026-05-21 02:19:16', '2026-05-21 03:08:13'),
(2, 'Moisturizer', 'Hydra Boost Moisturizer', 18.50, 40, 'Dry Skin', 'Hyaluronic Acid, Shea Butter', '2027-10-15', 'Deep hydration for soft and smooth skin', '2026-05-21T06-52-07.862880400_moisturizer.png', '2026-05-21 02:19:16', '2026-05-21 03:08:39'),
(3, 'Sunscreen', 'SPF 50 Sun Shield Cream', 22.00, 60, 'All Skin Types', 'Zinc Oxide, Titanium Dioxide', '2026-09-10', 'Broad spectrum UVA/UVB protection', '2026-05-21T06-52-07.862880400_sunscreen.png', '2026-05-21 02:19:16', '2026-05-21 03:09:00'),
(4, 'Serum', 'Vitamin C Brightening Serum', 25.99, 30, 'Dull Skin', 'Vitamin C, Niacinamide, Ferulic Acid', '2027-05-20', 'Brightens skin and reduces dark spots', '2026-05-21T06-52-07.862880400_serum.png', '2026-05-21 02:19:16', '2026-05-21 03:09:22'),
(5, 'Eye Care', 'Revive Eye Cream', 19.75, 35, 'Dark Circles', 'Caffeine, Peptides, Aloe Vera', '2027-08-30', 'Reduces puffiness and dark circles', '2026-05-21T06-52-07.862880400_eye-cream.png', '2026-05-21 02:19:16', '2026-05-21 03:10:55');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `passwordHash` varchar(255) NOT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `skinType` varchar(50) DEFAULT NULL,
  `registrationDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `isAdmin` tinyint(1) DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `name`, `email`, `passwordHash`, `phoneNumber`, `address`, `skinType`, `registrationDate`, `isAdmin`, `created_at`, `updated_at`) VALUES
(1, 'Admin', 'admin@gmail.com', '$2a$10$LfdzEq3z6E7w7LOBM7PU/effPoSaMcWQqoEzyBIsZ/lYjoOTQBYe6', '+977 9800000000', NULL, NULL, '2026-05-21 02:12:51', 1, '2026-05-21 02:12:51', '2026-05-21 02:15:12'),
(2, 'TestUser', 'testuser@gmail.com', '$2a$10$vMBeAPUY5na7SZ/p3KivD.QXynJ6a3M39ht3nap1L7/IP2GicxCBm', '+977 9800000000', NULL, NULL, '2026-05-21 02:14:03', 0, '2026-05-21 02:14:03', '2026-05-21 02:14:03'),
(3, 'DemoUser', 'demouser@gmail.com', '$2a$10$lpuj5HQzy7KJx01WMe/RVOR.MUp5vjjN7zYrozi6awb2/f9Q405Qu', '+977 9800000000', NULL, NULL, '2026-05-21 02:14:37', 0, '2026-05-21 02:14:37', '2026-05-21 02:14:37');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`categoryName`);

--
-- Indexes for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD PRIMARY KEY (`orderItemId`),
  ADD UNIQUE KEY `orderId` (`orderId`,`productId`),
  ADD KEY `productId` (`productId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productId`),
  ADD KEY `categoryName` (`categoryName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orderitems`
--
ALTER TABLE `orderitems`
  MODIFY `orderItemId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE,
  ADD CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`) ON DELETE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryName`) REFERENCES `categories` (`categoryName`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
