package persistence;

import domain.Client;

import java.util.Map;

public class PersistenceController {
    // Fields
    private ClientMapper clientMapper;


    // Constructors
    public PersistenceController() {
        clientMapper = new ClientMapper();
    }


    // Search clients - Methods
    public Map<Integer, Client> getMatchingClients(String searchTerm){
        return clientMapper.getMatchingClients(searchTerm);
    }


    // Add client - Methods
    public void addClient(Client client) {
        clientMapper.addClient(client);
    }
}
