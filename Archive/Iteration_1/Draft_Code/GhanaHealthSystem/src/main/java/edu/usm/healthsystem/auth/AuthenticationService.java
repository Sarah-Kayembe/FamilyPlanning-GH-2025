package edu.usm.healthsystem.auth;

/**
 * Service handling core authentication logic including registration and login.
 */
public class AuthenticationService {

    /**
     * Registers a new user in the system.
     * @param username Unique identifier for the user
     * @param name First name of the user
     * @param lastName Last name of the user
     * @param password Password to be hashed and stored
     */
    public void register(String username, String name, String lastName, String password) {
        // TODO: Implement user registration
    }

    /**
     * Authenticates a user attempting to log in.
     * @param username User's username
     * @param password User's password (to be verified against stored hash)
     */
    public void login(String username, String password) {
        // TODO: Implement login authentication
    }

}