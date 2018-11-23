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
}
