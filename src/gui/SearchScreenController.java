package gui;

import domain.ClientController;
import exceptions.EmptyClientListException;
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
        // TODO : implement AddClient screen and controller
        // Stage stage = (Stage) btnAdd.getScene().getWindow();
        // stage.setScene(new Scene(new AddClientScreenController(clientController)));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            clientController.searchClient(txfSearch.getText());

            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.setScene(new Scene(new ListScreenController(clientController)));
        } catch (EmptyClientListException e) {
            Alert noClientFoundAlert = new Alert(Alert.AlertType.WARNING);
            noClientFoundAlert.setTitle("Waarschuwing");
            noClientFoundAlert.setContentText("Er werden geen klanten gevonden met de opgegeven zoekterm");
            noClientFoundAlert.showAndWait();
        } catch (IllegalArgumentException e){
            Alert noClientFoundAlert = new Alert(Alert.AlertType.WARNING);
            noClientFoundAlert.setTitle("Waarschuwing");
            noClientFoundAlert.setContentText("Gelieve een geldige zoekterm in te geven");
            noClientFoundAlert.showAndWait();
        }
    }
}
