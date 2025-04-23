package edu.usm.healthsystem;

import edu.usm.healthsystem.controller.auth.AuthenticationController;
import edu.usm.healthsystem.service.auth.AuthenticationService;
import edu.usm.healthsystem.controller.patient.ClientController;
import edu.usm.healthsystem.service.client.ClientService;
import edu.usm.healthsystem.view.InventoryInterface;

import javax.swing.*;

/**
 * Main entry point for the Ghana Health System application.
 * This system manages patient records, employee access, and inventory for healthcare facilities.
 */
public class GhanaHealthSystem {

    /**
     * Main method that initializes the application controllers and services.
     * @param args Command line arguments (not currently used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryInterface::new);
    }

    /**
     * Generates monthly reports for the health system.
     * (Implementation pending)
     */
    public void generateMonthlyReports() {
        // TODO: Implement monthly report generation
    }

}