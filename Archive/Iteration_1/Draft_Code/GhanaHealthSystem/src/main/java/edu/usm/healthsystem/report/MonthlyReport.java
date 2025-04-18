package edu.usm.healthsystem.report;

import edu.usm.healthsystem.model.Client;

/**
 * Implementation of Report for generating monthly summary reports.
 */
public class MonthlyReport implements Report {

    /**
     * Generates a monthly summary report.
     * @param client The client to generate the report for (may be null for system-wide reports)
     */
    @Override
    public void generate(Client client) {
        // TODO: Implement monthly report generation
    }

}