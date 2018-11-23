package gui;

import domain.Client;
import domain.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ListScreenController extends VBox {
    // Fields
    @FXML
    private ListView<Client> lvClients;

    @FXML
    private Button btnBack;

    private ClientController clientController;


    // Constructors
    public ListScreenController(ClientController clientController) {
        this.clientController = clientController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error loading ListScreen.fxml");
        }

        // populate listview
        lvClients.setItems(clientController.getMatchingClients());

        // listener for selection model
        lvClients.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            clientController.selectClient(lvClients.getSelectionModel().getSelectedItem().getId());

            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(new ClientInfoScreenController(clientController)));
        });
    }


    // Handlers
    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene(new SearchScreenController(clientController)));
    }

}
