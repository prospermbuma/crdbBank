package com.celebrate.crdb_bank;

import com.celebrate.crdb_bank.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    // Implement start method in the Application class
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
