package com.celebrate.crdb_bank.Controllers.Client;

import com.celebrate.crdb_bank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProfileController implements Initializable {

    public TextField firstName;
    public TextField lastName;
    public PasswordField current_pass;
    public PasswordField new_pass;
    public PasswordField confirm_pass;
    public Button save_btn;
    public Label error_lbl;

    // Logger
    private static final Logger LOGGER = Logger.getLogger(ProfileController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateClientData();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error initializing dashboard: {0}", e.getMessage());
        }

    }
    // Method to populate the profile with client data
    private void populateClientData() throws SQLException {
        String clientPayeeAddress = getLoggedInClientPayeeAddress();
        if (clientPayeeAddress.isEmpty()) {
            LOGGER.log(Level.WARNING, "No logged-in client found.");
            return;
        }

        // Fetch client data from the database
        String query = "SELECT FirstName , LastName FROM Clients  WHERE PayeeAddress = ?";

        ResultSet rs;
        try (PreparedStatement preparedStatement = Model.getInstance().getDatabaseDriver().getConn().prepareStatement(query)) {
            preparedStatement.setString(1, clientPayeeAddress);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Populate UI elements with client data
                firstName.setText(rs.getString("FirstName"));
                lastName.setText(rs.getString("LastName"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing SQL query: {0}", e.getMessage());
        }
    }

    // Method to fetch the logged-in client's PayeeAddress
    private String getLoggedInClientPayeeAddress() {
        String payeeAddress = "";
        try {
            payeeAddress = Model.getInstance().getDatabaseDriver().getLoggedInClientPayeeAddress();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving client's payee address: {0}", e.getMessage());
        }
        return payeeAddress;
    }

}
