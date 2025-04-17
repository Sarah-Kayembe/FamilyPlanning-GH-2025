package edu.usm.healthsystem.model.client;

import edu.usm.healthsystem.model.familyplanning.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a patient in the health system.
 * Implements Client interface for authentication and identification.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Client {

    // Existing fields
    private String username;
    private String name;
    private String lastName;
    private String passwordHash;
    private String medicalHistory;

    // New fields for Family Planning Client Register
    private String date; // Date of registration
    private String nhisNumber; // NHIS Reg. No.
    private String cardNumber; // Card No
    private String maritalStatus; // Marital Status
    private String sex; // Sex
    private String address; // Address (Location/WP/Community/Hse No.)
    private String methodOfChoice; // Method of Choice
    private boolean firstUse; // 1st ever use of method (Y/N)
    private int age; // Age
    private int parity; // Parity
    private Map<Month, Item> monthlyUsage; // Updated to store Item objects

    // Existing overridden methods
    @Override
    public String getUsername() { return username; }

    @Override
    public String getName() { return name; }

    @Override
    public String getLastName() { return lastName; }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    // Helper method to set monthly usage
    public void setMonthlyUsage(Month month, Item item) {
        if (monthlyUsage == null) {
            monthlyUsage = new HashMap<>();
        }
        monthlyUsage.put(month, item);
    }

    public void setReferred(Month month) {
        if (monthlyUsage == null) {
            monthlyUsage = new HashMap<>();
        }
        monthlyUsage.put(month, new Item("REFERRED", 0));
    }

}