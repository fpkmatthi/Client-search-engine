package gui;

import domain.ClientController;
import exceptions.EmptyClientListException;
import gui.addClient.AddClientScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchScreenController extends GridPane {
    // Fields
    @FXML
    private TextField txfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdd;

    private ClientController clientController;


    // Constructor
    public SearchScreenController(ClientController clientController) {
        this.clientController = clientController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error loading SearchScreen.fxml");
        }
    }


    // Handlers
    @FXML
    void btnAddOnAction(ActionEvent event) {
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        stage.setScene(new Scene(new AddClientScreenController(clientController)));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            clientController.searchClient(txfSearch.getText());

            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.setScene(new Scene(new ListScreenController(clientController)));
        } catch (EmptyClientListException e) {
            showAlert("Er werden geen klanten gevonden met de opgegeven zoekterm");
        } catch (IllegalArgumentException e){
            showAlert("Gelieve een geldige zoekterm in te geven");
        }
    }

    private void showAlert(String contentText){
        Alert noClientFoundAlert = new Alert(Alert.AlertType.WARNING);
        noClientFoundAlert.setTitle("Waarschuwing");
        noClientFoundAlert.setHeaderText(null);
        noClientFoundAlert.setContentText(contentText);
        noClientFoundAlert.showAndWait();

    }
}
