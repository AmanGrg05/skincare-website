package com.PearlSkin.dao;

import com.PearlSkin.entity.Product;
import com.PearlSkin.entity.TopProduct;

import java.sql.Connection;
import java.util.ArrayList;

public interface ProductDao {
    boolean insertProduct(Product product);
    ArrayList<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    Product getProductById(int id);
    ArrayList<Product> getProductsByName(String name);
    ArrayList<Product> getFeaturedProducts();
    int countProducts();
    ArrayList<TopProduct> getTopProducts(int limit);
    boolean reduceStock(Connection conn, int productId, int stockQuantity);
}
