package edu.usm.healthsystem.model.familyplanning;

import edu.usm.healthsystem.model.client.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyPlanningPatient implements Patient {

    // Client fields
    private String username;
    private String name;
    private String lastName;
    private String passwordHash;

    // Patient fields
    private String date; // Date of registration
    private String nhisNumber; // NHIS Reg. No.
    private String cardNumber; // Card No
    private String maritalStatus; // Marital Status
    private String sex; // Sex
    private String address; // Address
    private int age; // Age

    // Family Planning fields
    private String medicalHistory;
    private boolean firstUse;
    private String methodOfChoice;
    private int parity;
    private Map<Month, Item> monthlyUsage;

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // Helper methods
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