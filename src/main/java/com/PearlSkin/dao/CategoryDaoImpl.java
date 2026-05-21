package com.PearlSkin.dao;

import com.PearlSkin.entity.Category;
import com.PearlSkin.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories ORDER BY categoryName";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                var rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Category category = new Category(
                        rs.getString("categoryName"),
                        rs.getString("description")
                );


                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    // ---------------- ADD CATEGORY ----------------
    @Override
    public boolean addCategory(Category category) {

        String sql = "INSERT INTO categories (categoryName, description) VALUES (?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // ---------------- UPDATE CATEGORY ----------------
    @Override
    public boolean updateCategory(Category category) {

        String sql = "UPDATE categories SET description = ? WHERE categoryName = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, category.getDescription());
            ps.setString(2, category.getCategoryName());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean deleteCategory(String categoryName) {

        String sql = "DELETE FROM categories WHERE categoryName = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, categoryName);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}