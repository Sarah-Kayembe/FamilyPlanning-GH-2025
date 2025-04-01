package edu.usm.healthsystem;

import edu.usm.healthsystem.auth.AuthenticationController;
import edu.usm.healthsystem.auth.AuthenticationService;
import edu.usm.healthsystem.client.ClientController;
import edu.usm.healthsystem.client.ClientService;

public class GhanaHealthSystem {

    public static void main(String[] args) {
        AuthenticationController authenticationController = new AuthenticationController(new AuthenticationService());
        ClientController clientController = new ClientController(new ClientService());
    }

    public void generateMonthlyReports() {}

}