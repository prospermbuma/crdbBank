package com.celebrate.crdb_bank.Controllers.Client;

import com.celebrate.crdb_bank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionsController implements Initializable {
    public ListView<String> transactions_listview;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(TransactionsController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String clientPayeeAddress = getLoggedInClientPayeeAddress();
        try {
            // Fetch transaction history
            populateTransactionList(clientPayeeAddress);
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error populating TransactionList: {0}", e.getMessage());
        }
    }

    // Method to populate the transaction list
    private void populateTransactionList(String payeeAddress) throws SQLException {
        DashboardController.populateTransList(payeeAddress, transactions_listview, LOGGER);
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
