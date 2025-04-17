package edu.usm.healthsystem.controller.auth;

import edu.usm.healthsystem.service.auth.AuthenticationService;

/**
 * Controller for handling authentication-related requests.
 * Acts as an intermediary between the UI and AuthenticationService.
 */
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Constructs an AuthenticationController with the specified service.
     * @param authenticationService The authentication service to use
     */
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

}