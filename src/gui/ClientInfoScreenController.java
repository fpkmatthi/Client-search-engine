package gui;

import domain.Address;
import domain.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ClientInfoScreenController extends GridPane {
    // Fields
    @FXML
    private Label lblName;

    @FXML
    private ListView<Address> lvAddresses;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRemoveAddress;

    @FXML
    private Button btnEditAddress;

    @FXML
    private Button btnAddAddress;

    @FXML
    private Button btnEditClient;

    @FXML
    private Button btnRemoveClient;

    private ClientController clientController;


    // Constructors
    public ClientInfoScreenController(ClientController clientController) {
        this.clientController = clientController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInfoScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error loading ClientSInfoScreen.fxml");
        }

    }


    // Handlers
    @FXML
    public void initialize() {
        btnRemoveAddress.setDisable(true);
        btnEditAddress.setDisable(true);
        lblName.setText(clientController.getClientName());
        lvAddresses.setItems(clientController.getClientAddresses());
        lvAddresses.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            btnRemoveAddress.setDisable(false);
            btnEditAddress.setDisable(false);
        });
    }

    // Handlers
    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene(new ListScreenController(clientController)));
    }

    @FXML
    void btnEditClientOnAction(ActionEvent event) {
    }

    @FXML
    void btnRemoveClientOnAction(ActionEvent event) {
    }

    @FXML
    void btnRemoveAddressOnAction(ActionEvent event) {
     }

    @FXML
    void btnAddAddressOnAction(ActionEvent event) {
    }

    @FXML
    void btnEditAddressOnAction(ActionEvent event) {
    }

}
