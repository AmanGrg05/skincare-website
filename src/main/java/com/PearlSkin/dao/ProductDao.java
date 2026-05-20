package com.PearlSkin.dao;

import com.PearlSkin.entity.Product;
import com.PearlSkin.entity.TopProduct;

import java.util.ArrayList;

public interface ProductDao {
    boolean insertProduct(Product product);
    ArrayList<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean deleteProduct(int productId);
    Product getProductById(int productId);
    ArrayList<Product> getProductsByName(String productName);
    ArrayList<Product> getFeaturedProducts();
    int countProducts();
    ArrayList<TopProduct> getTopProducts(int limit);
}
