package persistence;

import domain.Address;
import domain.Client;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ClientMapper {
    // Fields
    private final String JDBC_DRIVER = "org.sqlite.JDBC";
    private final String JDBC_URL = "jdbc:sqlite:src/persistence/clientAddressDatabase.db";
    private Connection connection;


    // Constructors
    public ClientMapper() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error in class: ClientMapper");
        }
    }


    // Connection
    private void openConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }
    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to close database connection.");
        }
    }


    // Init db - Methods
    private void emptyDb() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement emptyAddress = connection.prepareStatement("delete from address");
             PreparedStatement emptyClient = connection.prepareStatement("delete from client")
        ) {
            emptyAddress.executeUpdate();
            emptyClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createDb() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement createClient = connection.prepareStatement("CREATE TABLE client (" +
                     "clientID INTEGER UNIQUE," +
                     "firstname TEXT NOT NULL," +
                     "lastname TEXT NOT NULL" +
                     "PRIMARY KEY(clientID)" +
                     ");");
             PreparedStatement createAddress = connection.prepareStatement("CREATE TABLE address (\n" +
                     "trackNr INTEGER," +
                     "city TEXT NOT NULL," +
                     "street TEXT NOT NULL," +
                     "houseNr INTEGER NOT NULL," +
                     "zipcode INTEGER NOT NULL," +
                     "clientID INTEGER NOT NULL," +
                     "FOREIGN KEY(clientID) REFERENCES client(clientID)" +
                     ");")
        ) {
            createClient.executeUpdate();
            createAddress.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Search Client - Methods
    public Map<Integer, Client> getMatchingClients(String searchTerm) {
        Map<Integer, Client> clients = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement getClients = connection.prepareStatement("select clientID, firstname, lastname from client where firstname like ? or lastname like ?");
             PreparedStatement getAddresses = connection.prepareStatement("select trackNr, city, street, houseNr, zipcode from address where clientID = ?")
        ) {

            getClients.setString(1, '%' + searchTerm + '%');
            getClients.setString(2, '%' + searchTerm + '%');

            ResultSet rs1 = getClients.executeQuery();

            while (rs1.next()) {
                Client client = new Client(rs1.getString("firstname"), rs1.getString("lastname"), rs1.getInt("clientID"));

                getAddresses.setInt(1, client.getId());
                ResultSet rs2 = getAddresses.executeQuery();

                while (rs2.next()) {
                    client.addAddress(rs2.getInt("trackNr"),
                            rs2.getString("city"),
                            rs2.getString("street"),
                            rs2.getInt("houseNr"),
                            rs2.getInt("zipcode"));
                }

                clients.put(client.getId(), client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    // Add client - Methods
    public void addClient(Client client) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement addClient = connection.prepareStatement("insert into client (firstname, lastname) values (?, ?)");
             PreparedStatement addAddress = connection.prepareStatement("insert into address values (?, ?, ?, ?, ?, ?)");
             PreparedStatement getLastInsertedId = connection.prepareStatement("select last_insert_rowid()")
        ) {
            addClient.setString(1, client.getFirstname());
            addClient.setString(2, client.getLastname());
            addClient.executeUpdate();

            ResultSet rs = getLastInsertedId.executeQuery();
            int clientID = 0;
            if (rs.next()) {
                clientID = rs.getInt("last_insert_rowid()");
            }
            for (Address address : client.getAddresses().values()) {
                addAddress.setInt(1, address.getTrackNr());
                addAddress.setString(2, address.getCity());
                addAddress.setString(3, address.getStreet());
                addAddress.setInt(4, address.getHouseNr());
                addAddress.setInt(5, address.getZipcode());
                addAddress.setInt(6, clientID);

                addAddress.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
