package com.celebrate.crdb_bank.Views;

import com.celebrate.crdb_bank.Controllers.Client.ClientCellController;
import com.celebrate.crdb_bank.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientCellFactory extends ListCell<Client> {
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ViewFactory.class.getName());

    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/ClientCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
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
