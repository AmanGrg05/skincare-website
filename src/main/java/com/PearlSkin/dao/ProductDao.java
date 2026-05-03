package com.PearlSkin.dao;

import com.PearlSkin.entity.Product;

import java.util.ArrayList;

public interface ProductDao {
    boolean insertProduct(Product product);
    ArrayList<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    Product getProductById(int id);
    ArrayList<Product> getProductsByName(String name);
}
