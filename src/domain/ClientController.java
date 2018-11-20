package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientController {
    // Fields
    private ClientRepository clientRepository;
    private Client client;


    // Constructors
    public ClientController() {
        clientRepository = new ClientRepository();
    }


    // Search client - Methods
    public void searchClient(String searchTerm) {
        clientRepository.searchClient(searchTerm);
    }
    public ObservableList<Client> getMatchingClients() {
        return FXCollections.observableList(clientRepository.getMatchingClients());
    }

    public void selectClient(int id) {
        this.client = clientRepository.selectClient(id);
    }
    public String getClientName() {
        return String.format("%s %s", client.getFirstname(), client.getLastname());
    }
    public ObservableList<Address> getClientAddresses() {
        return FXCollections.observableArrayList(client.getAddresses().values());
    }

    public Address getAddress(int trackNr){
        return client.getAddress(trackNr);
    }



}
