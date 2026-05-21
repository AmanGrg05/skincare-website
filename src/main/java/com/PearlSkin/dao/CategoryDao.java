package com.PearlSkin.dao;

import com.PearlSkin.entity.Category;

import java.util.List;

/**
 * Data Access Object (DAO) interface for Category-related database operations.
 *
 * This interface defines the basic CRUD operations for the Category entity.
 * Implementing classes are responsible for interacting with the database
 * to perform insert, update, and delete operations.
 *
 * Any database-related errors should be handled in the implementation layer
 * and may result in runtime exceptions.
 */

public interface CategoryDao {
    /**
     * Inserts a new category into the database.
     *
     * This method persists a Category object by mapping its fields
     * (such as category name or description) into the corresponding
     * database table.
     *
     * @param category the Category object containing category details
     * @return true if the category is successfully added; false otherwise
     * @throws RuntimeException if a database access error occurs
     */
    boolean addCategory(Category category);

    /**
     * Updates an existing category record in the database.
     *
     * This method modifies category details using the information
     * provided in the Category object. The category is identified
     * using its unique ID.
     *
     * @param category the Category object containing updated data
     * @return true if the category is successfully updated; false otherwise
     * @throws RuntimeException if a database access error occurs
     */
    boolean updateCategory(Category category);
    /**
     * Deletes a category from the database using its unique ID.
     *
     * This method permanently removes the category record associated
     * with the given ID from the database.
     *
     * @param id the unique identifier of the category to be deleted
     * @return true if the deletion is successful; false otherwise
     * @throws RuntimeException if a database access error occurs
     */

    List<Category> getAllCategories();
    boolean deleteCategory(String categoryName);
}
