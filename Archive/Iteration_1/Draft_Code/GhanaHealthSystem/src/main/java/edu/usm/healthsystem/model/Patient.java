package edu.usm.healthsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a patient in the health system.
 * Implements Client interface for authentication and identification.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Client {

    private String username;
    private String name;
    private String lastName;
    private String passwordHash;
    private String medicalHistory;

    @Override
    public String getUsername() { return username; }

    @Override
    public String getName() { return name; }

    @Override
    public String getLastName() { return lastName; }

    @Override
    public String getPassword() {
        return passwordHash;  // Fixed: Return passwordHash instead of null
    }

}