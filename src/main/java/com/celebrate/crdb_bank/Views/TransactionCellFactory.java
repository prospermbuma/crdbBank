package com.celebrate.crdb_bank.Views;

import com.celebrate.crdb_bank.Controllers.Client.TransactionCellController;
import com.celebrate.crdb_bank.Models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// INHERITANCE
public class TransactionCellFactory extends ListCell<Transaction> {
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ViewFactory.class.getName());

    @Override
    protected void updateItem(Transaction transaction, boolean empty) {
        super.updateItem(transaction, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/TransactionCell.fxml"));
            TransactionCellController controller = new TransactionCellController(transaction);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Graphic not found: {0}", ex.getMessage());
            }
        }
    }
}