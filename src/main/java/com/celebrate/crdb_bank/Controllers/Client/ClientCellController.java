package com.celebrate.crdb_bank.Controllers.Client;

import com.celebrate.crdb_bank.Models.Client;
import com.celebrate.crdb_bank.Models.Transaction;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {


    private final Client client;

    // Constructor
    public ClientCellController(Client client) {
        this.client = client;
    }

    // Getter
    public Client getClient() {
        return client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
