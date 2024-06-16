package com.celebrate.crdb_bank.Controllers.Client;

import com.celebrate.crdb_bank.Models.Model;
import com.celebrate.crdb_bank.Models.SessionManager;
import com.celebrate.crdb_bank.Models.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label checking_bal;
    public Label checking_acc_num;
    public Label savings_bal;
    public Label savings_acc_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView<String> transaction_listview;

    // Logger
    private static final Logger LOGGER = Logger.getLogger(DashboardController.class.getName());

    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateDashboard();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error initializing dashboard: {0}", e.getMessage());
        }
    }

    // Method to populate the dashboard with client data
    private void populateDashboard() throws SQLException {
        String clientPayeeAddress = getLoggedInClientPayeeAddress();
        if (clientPayeeAddress.isEmpty()) {
            LOGGER.log(Level.WARNING, "No logged-in client found.");
            return;
        }

        // Fetch client data from the database
        String query = "SELECT cl.FirstName AS FirstName, ch.Balance AS CheckingBalance, ch.AccountNumber AS CheckingAccountNumber, sv.Balance AS SavingsBalance , " +
                "sv.AccountNumber AS SavingsAccountNumber FROM Clients cl INNER JOIN CheckingAccounts ch ON cl.PayeeAddress = ch.Owner JOIN SavingsAccounts sv ON cl.PayeeAddress = sv.Owner WHERE cl.PayeeAddress = ?";

        ResultSet rs;
        try (PreparedStatement preparedStatement = Model.getInstance().getDatabaseDriver().getConn().prepareStatement(query)) {
            preparedStatement.setString(1, clientPayeeAddress);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Populate UI elements with client data
                user_name.setText("Hi, " + rs.getString("FirstName"));
                checking_bal.setText("TZS " + rs.getString("CheckingBalance"));
                checking_acc_num.setText(rs.getString("CheckingAccountNumber"));
                savings_bal.setText("TZS " + rs.getString("SavingsBalance"));
                savings_acc_num.setText(rs.getString("SavingsAccountNumber"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing SQL query: {0}", e.getMessage());
        }
        // Fetch transaction history
        populateTransactionList(clientPayeeAddress);
        // Set login date from session
        login_date.setText(SessionManager.getInstance().getSessionDate());
    }
    // Method to populate the transaction list
    private void populateTransactionList(String payeeAddress) throws SQLException {
        populateTransList(payeeAddress, transaction_listview, LOGGER);
    }

    static void populateTransList(String payeeAddress, ListView<String> transactionListview, Logger logger) throws SQLException {
        String query = "SELECT Sender, Receiver, Amount, Date, Message FROM Transactions WHERE Sender = ? OR Receiver = ?";
        ResultSet rs;
        try (PreparedStatement preparedStatement = Model.getInstance().getDatabaseDriver().getConn().prepareStatement(query)) {
            preparedStatement.setString(1, payeeAddress);
            preparedStatement.setString(2, payeeAddress);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String transactions = String.format("%s -> %s: %s on %s (Message: %s)",
                        rs.getString("Sender"), rs.getString("Receiver"),
                        rs.getString("Amount"), rs.getString("Date"),
                        rs.getString("Message"));
                transactionListview.getItems().add(transactions);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching transaction history: {0}", e.getMessage());
            throw e;
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
