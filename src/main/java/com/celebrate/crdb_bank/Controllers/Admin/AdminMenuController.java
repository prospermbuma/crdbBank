package com.celebrate.crdb_bank.Controllers.Admin;

import com.celebrate.crdb_bank.Models.Model;
import com.celebrate.crdb_bank.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    // Abstract Setter Method - Implementing polymorphism by overriding initialize method of abstract class Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    // Setter Method - Add Event Listener to buttons
    public void addListener() {
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onClient());
        deposit_btn.setOnAction(event -> onDeposit());
        logout_btn.setOnAction(event -> onLogout());
    }

    // Setter Method - On Create-Client button click event set admin selected menu item to Dashboard
    private void onCreateClient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);
    }

    // Setter Method - On transaction button click event set admin selected menu item to Transactions
    private void onClient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENT);
    }

    // Setter Method - On accounts button click event set admin selected menu item to Profile
    private void onDeposit() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
    }

    // Setter Method - On accounts button click event set admin selected menu item to Logout
    private void onLogout() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.LOGOUT);
    }


}
