package com.celebrate.crdb_bank.Controllers.Client;

import com.celebrate.crdb_bank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountsController implements Initializable {
    public Label ch_acc_num;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_bal;
    public Label sv_acc_num;
    public Label withdrawal_limit;
    public Label sv_acc_date;
    public Label sv_acc_bal;
    public TextField amount_to_sv;
    public Button trans_to_sv_btn;
    public TextField amount_to_ch;
    public Button trans_to_ch_btn;

    // Logger
    private static final Logger LOGGER = Logger.getLogger(AccountsController.class.getName());

    // Abstract Setter Method - Implementing polymorphism by overriding initialize method of abstract class Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateAccounts();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error initializing accounts: {0}", e.getMessage());
        }
    }

    // Method to populate the dashboard with client data
    private void populateAccounts() throws SQLException {
        String clientPayeeAddr = getLoggedInClientPayeeAddress();
        if (clientPayeeAddr.isEmpty()) {
            LOGGER.log(Level.WARNING, "No logged-in client found.");
            return;
        }

        // Fetch client data from the database
        String query = "SELECT cl.Date AS Date, ch.Balance AS CheckingBalance, ch.AccountNumber AS CheckingAccountNumber,  ch.TransactionLimit AS TransactionLimit, sv.Balance AS SavingsBalance , " +
                "sv.AccountNumber AS SavingsAccountNumber, sv.WithdrawalLimit AS WithdrawalLimit FROM Clients cl INNER JOIN CheckingAccounts ch ON cl.PayeeAddress = ch.Owner JOIN SavingsAccounts sv ON cl.PayeeAddress = sv.Owner WHERE cl.PayeeAddress = ?";

        ResultSet rs;
        try (PreparedStatement preparedStatement = Model.getInstance().getDatabaseDriver().getConn().prepareStatement(query)) {
            preparedStatement.setString(1, clientPayeeAddr);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Populate UI elements with client data
                transaction_limit.setText(rs.getString("TransactionLimit"));
                ch_acc_bal.setText("TZS " + rs.getString("CheckingBalance"));
                ch_acc_num.setText(rs.getString("CheckingAccountNumber"));
                ch_acc_date.setText(rs.getString("Date"));
                withdrawal_limit.setText("TZS " + rs.getString("WithdrawalLimit"));
                sv_acc_bal.setText("TZS " + rs.getString("SavingsBalance"));
                sv_acc_num.setText(rs.getString("SavingsAccountNumber"));
                sv_acc_date.setText(rs.getString("Date"));
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
