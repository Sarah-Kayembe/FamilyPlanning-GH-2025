package edu.usm.healthsystem.client;

/**
 * Controller for handling client-related operations.
 * Acts as an intermediary between the UI and ClientService.
 */
public class ClientController {

    public final ClientService clientService;

    /**
     * Constructs a ClientController with the specified service.
     * @param clientService The client service to use
     */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    
    
}