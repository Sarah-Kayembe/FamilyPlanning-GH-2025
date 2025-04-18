package edu.usm.healthsystem.model;

/**
 * Interface representing a client in the health system (can be either a patient or employee).
 */
public interface Client {

    /**
     * @return The username for authentication
     */
    String getUsername();

    /**
     * @return The first name of the client
     */
    String getName();

    /**
     * @return The last name of the client
     */
    String getLastName();

    /**
     * @return The hashed password of the client
     */
    String getPassword();

}