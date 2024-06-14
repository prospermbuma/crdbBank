package com.celebrate.crdb_bank.Controllers.Client;

import com.celebrate.crdb_bank.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_lbl;

    private Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (transaction != null) {
            trans_date_lbl.setText(transaction.getDate().toString());
            sender_lbl.setText(transaction.getSender().toString());
            receiver_lbl.setText(transaction.getReceiver().toString());
            amount_lbl.setText(transaction.getAmount().toString());

            // Determine whether to show in or out icon based on transaction direction
            if (transaction.isIncoming()) {
                in_icon.setVisible(true);
                out_icon.setVisible(false);
            } else {
                in_icon.setVisible(false);
                out_icon.setVisible(true);
            }
        }
    }

    // Setter method to set the transaction
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
