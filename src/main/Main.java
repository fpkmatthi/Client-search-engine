package main;

import domain.ClientController;
import gui.SearchScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientController clientController = new ClientController();
        SearchScreenController searchScreenController = new SearchScreenController(clientController);

        primaryStage.setTitle("Client Search Engine");
        primaryStage.setScene(new Scene(searchScreenController));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
