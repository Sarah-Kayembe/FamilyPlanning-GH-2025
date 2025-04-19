package edu.usm.healthsystem.model.client;

/**
 * Represents a general client in the system with basic identity and authentication fields.
 */
public interface Client {

    /**
     * Gets the username for authentication.
     *
     * @return the client's username
     */
    String getUsername();

    /**
     * Sets the username for authentication.
     *
     * @param username the client's username
     */
    void setUsername(String username);

    /**
     * Gets the client's first name.
     *
     * @return the first name
     */
    String getName();

    /**
     * Sets the client's first name.
     *
     * @param name the first name
     */
    void setName(String name);

    /**
     * Gets the client's last name.
     *
     * @return the last name
     */
    String getLastName();

    /**
     * Sets the client's last name.
     *
     * @param lastName the last name
     */
    void setLastName(String lastName);

    /**
     * Gets the client's hashed password.
     *
     * @return the hashed password
     */
    String getPassword();

    /**
     * Sets the client's hashed password.
     *
     * @param passwordHash the hashed password
     */
    void setPassword(String passwordHash);

}