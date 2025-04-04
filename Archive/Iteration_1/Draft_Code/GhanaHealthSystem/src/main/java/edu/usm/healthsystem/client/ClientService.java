package edu.usm.healthsystem.client;

import edu.usm.healthsystem.model.Client;

/**
 * Service handling core operations related to clients (patients and employees).
 */
public class ClientService {

    /**
     * Displays information about a client.
     * @param client The client whose information to view
     */
    public void viewInfo(Client client) {
        // TODO: Implement client info viewing
    }

    /**
     * Modifies information about a client.
     * @param client The client with updated information
     */
    public void editInfo(Client client) {
        // TODO: Implement client info editing
    }

    /**
     * Searches for a client by name or username.
     * @param query Search term (name or username)
     * @return The matching client or null if not found
     */
    public Client searchClient(String query) {
        // TODO: Implement client search
        return null;
    }

    /**
     * Creates a report for a specific client.
     * @param client The client to generate the report for
     * @return The generated report as a string
     */
    public String createReport(String client) {
        // TODO: Implement report generation
        return "";
    }

    /**
     * Deletes a client from the system.
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteClient() {
        // TODO: Implement client deletion
        return false;
    }

}