package com.PearlSkin.dao;

import com.PearlSkin.entity.Category;
public interface CategoryDao {
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(int id);
}
