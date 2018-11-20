package domain;

import persistence.PersistenceController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientRepository {
    // Fields
    private Map<Integer, Client> clients;
    private PersistenceController persistenceController;


    // Constructors
    public ClientRepository() {
        persistenceController = new PersistenceController();
    }


    // Search client - Methods
    public void searchClient(String searchTerm) {
        clients = persistenceController.getMatchingClients(searchTerm);
    }
    public List<Client> getMatchingClients() {
        return new ArrayList<>(clients.values());
    }
    public Client selectClient(int id) {
        return clients.get(id);
    }



}
