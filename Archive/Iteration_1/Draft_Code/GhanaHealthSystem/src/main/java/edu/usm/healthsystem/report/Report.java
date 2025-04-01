package edu.usm.healthsystem.report;

import edu.usm.healthsystem.model.Client;

public interface Report {

    void generate(Client client);

}
