package edu.usm.healthsystem.model.report;

import edu.usm.healthsystem.model.client.Client;

/**
 * Interface for generating various types of reports in the health system.
 */
public interface Report {

    /**
     * Generates a report for the specified client.
     * @param client The client (patient or employee) to generate the report for
     */
    void generate(Client client);
}