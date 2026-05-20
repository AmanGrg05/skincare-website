package com.PearlSkin.dao;

import com.PearlSkin.entity.User;

/**
 * Data Access Object (DAO) interface for managing User-related database operations.
 *
 * This interface defines core CRUD operations for the User entity, including
 * creation, retrieval, updating, deletion, and aggregation of user data.
 * Implementations of this interface are responsible for handling actual
 * database connectivity and SQL execution.
 *
 * Any database access errors during execution are expected to be handled
 * at the implementation level and may result in runtime exceptions.
 */
public interface UserDao {

    /**
     * Retrieves the total number of customers stored in the system.
     *
     * This method queries the database to count all user records
     * classified as customers.
     *
     * @return the total number of customers as an integer
     * @throws RuntimeException if a database access error occurs
     */
    int countCustomers();

    /**
     * Inserts a new user record into the database.
     *
     * This method persists a User object into the database after mapping
     * its fields (such as name, email, password, and role) to the
     * corresponding database columns.
     *
     * @param user the User object containing all required user details
     * @return true if the user is successfully inserted; false otherwise
     * @throws RuntimeException if a database access error occurs
     */
    boolean addUser(User user);

    /**
     * Retrieves a user record based on the username.
     *
     * This method searches the database for a user whose name matches
     * the provided input value.
     *
     * @param Name the username used for searching the user record
     * @return the matching User object if found; otherwise null
     * @throws RuntimeException if a database access error occurs
     */
    User findByName(String Name);

    /**
     * Retrieves a user record based on the email address.
     *
     * This method queries the database to find a user associated with
     * the given email address.
     *
     * @param email the email address used to locate the user
     * @return the matching User object if found; otherwise null
     * @throws RuntimeException if a database access error occurs
     */

    User findByEmail(String email);

    /**
     * Updates an existing user record in the database.
     *
     * This method modifies user details in the database using the
     * information provided in the User object. The user is identified
     * using its unique identifier.
     *
     * @param user the User object containing updated information
     * @return true if the update is successful; false otherwise
     * @throws RuntimeException if a database access error occurs
     */

    boolean updateUser(User user);

    /**
     * Deletes a user record from the database.
     *
     * This method permanently removes a user entry identified by its ID.
     *
     * @param id the unique identifier of the user to be deleted
     * @return true if the deletion is successful; false otherwise
     * @throws RuntimeException if a database access error occurs
     */
    boolean deleteUser(int id);
}
