package domain;

import exceptions.EmptyClientListException;
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
    public void searchClient(String searchTerm) throws EmptyClientListException, IllegalArgumentException {
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


    // Add client - Methods
    public void addClient(String firstname, String lastname, int trackNr, String city, String street, int houseNr, int zipcode) {
        Client client = new Client(firstname, lastname);
		client.addAddress(trackNr, city, street, houseNr, zipcode);
		clientRepository.addClient(client);
    }

}
