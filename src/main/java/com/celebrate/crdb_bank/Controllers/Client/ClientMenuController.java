package com.celebrate.crdb_bank.Controllers.Client;

import com.celebrate.crdb_bank.Models.Model;
import com.celebrate.crdb_bank.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    // Abstract Setter Method - Implementing polymorphism by overriding initialize method of abstract class Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    // Setter Method - Add Event Listener to buttons
    public void addListener() {
        dashboard_btn.setOnAction(event -> onDashboard());
        transaction_btn.setOnAction(event -> onTransactions());
        accounts_btn.setOnAction(event -> onAccounts());
        profile_btn.setOnAction(event -> onProfile());
        logout_btn.setOnAction(event -> onLogout());
        report_btn.setOnAction(event -> onReport());
    }

    // Setter Method - On dashboard button click event set client selected menu item to Dashboard
    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    // Setter Method - On transaction button click event set client selected menu item to Transactions
    private void onTransactions() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    // Setter Method - On accounts button click event set client selected menu item to Accounts
    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    // Setter Method - On accounts button click event set client selected menu item to Profile
    private void onProfile() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFILE);
    }

    // Setter Method - On accounts button click event set client selected menu item to Logout
    private void onLogout() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.LOGOUT);
    }

    // Setter Method - On accounts button click event set client selected menu item to Report
    private void onReport() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.REPORT);
    }

}
