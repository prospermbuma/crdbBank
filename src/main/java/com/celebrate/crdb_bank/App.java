package com.celebrate.crdb_bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.Objects;

public class App extends Application {
    // Implement start method in the Application class
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Client/ClientMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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
}
