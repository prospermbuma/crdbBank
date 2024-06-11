package com.celebrate.crdb_bank.Views;

import com.celebrate.crdb_bank.Controllers.Client.ClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewFactory {
    // Client  Views
    // Encapsulation
    private AnchorPane dashboardView;
    private static final Logger LOGGER = Logger.getLogger(ViewFactory.class.getName());

    // Default constructor
    public ViewFactory() {
    }

    // Getter/Accessor Method
    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            } catch (IOException ex) {
                // e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Dashboard not found: {0}", ex.getMessage());
            }
        }
        return dashboardView;
    }

    // Setter/Mutator Method - Show Login Window
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    // Setter/Mutator Method - Show Client Window
    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    // Setter/Mutator Method - Create Stage
    public void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Window not found: {0}", ex.getMessage());
        }
        Stage stage = new Stage();
        // Place the scene in the stage
        stage.setScene(scene);
        // Set the stage title
        stage.setTitle("CRDB - Bank Management System");
        // Set Application logo
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/logo/logo2.png"))));
        // Prevent/Allow users to resize the stage
        stage.setResizable(false);
        // Display the stage
        stage.show();
    }

    // Setter Method - Close stage
    public void closeStage(Stage stage) {
        stage.close();
    }

}
