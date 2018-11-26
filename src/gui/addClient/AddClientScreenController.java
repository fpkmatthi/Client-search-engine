package gui.addClient;

import domain.ClientController;
import gui.SearchScreenController;
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

public class AddClientScreenController extends GridPane {
    // Fields
    @FXML
    private TextField txfFirstname;

    @FXML
    private TextField txfLastname;

    @FXML
    private TextField txfCity;

    @FXML
    private TextField txfStreet;

    @FXML
    private TextField txfHouseNr;

    @FXML
    private TextField txfZipcode;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txfTrackNr;

    private ClientController clientController;

    // Constructors
    public AddClientScreenController(ClientController clientController) {
        this.clientController = clientController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddClientScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error loading AddClientScreen.fxml");
        }
    }

    // Handlers
    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.setScene(new Scene(new SearchScreenController(clientController)));
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        // TODO : provide individual exception for every field
        try {
            clientController.addClient(
                    txfFirstname.getText(),
                    txfLastname.getText(),
                    Integer.parseInt(txfTrackNr.getText()),
                    txfCity.getText(),
                    txfStreet.getText(),
                    Integer.parseInt(txfHouseNr.getText()),
                    Integer.parseInt(txfZipcode.getText())
            );

            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.setScene(new Scene(new SearchScreenController(clientController)));
        } catch(IllegalArgumentException e){
            Alert dataFormatAlert = new Alert(Alert.AlertType.ERROR);
            dataFormatAlert.setTitle("Error");
            dataFormatAlert.setContentText("Kijk na of alles juist werd ingegeven");
            dataFormatAlert.showAndWait();
        }
    }
}
