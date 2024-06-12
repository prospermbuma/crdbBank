package com.celebrate.crdb_bank.Views;

import com.celebrate.crdb_bank.Controllers.Admin.AdminController;
import com.celebrate.crdb_bank.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewFactory {
    /*===========================================
    # Login Account Type Property
    ============================================*/
    private AccountType loginAccountType;

    /*===========================================
    # Logger
    ============================================*/
    private static final Logger LOGGER = Logger.getLogger(ViewFactory.class.getName());

    /*===========================================
    # Client View Properties
    ============================================*/
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;

    /*===========================================
     # Admin View Properties
     ============================================*/
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane createClientView;
    private AnchorPane clientsView;

    /*===========================================
    # Class constructor
    ============================================*/
    public ViewFactory() {
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    /*===========================================
    # Login Account Type Methods
    ============================================*/
    // Getter Method - Get Login Account Type
    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    // Setter Method
    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*===========================================
     # Client View Methods
    ============================================*/
    // Getter Method - Get Client Selected Menu Item
    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    // Getter Method - Get Dashboard View
    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Dashboard not found: {0}", ex.getMessage());
            }
        }
        return dashboardView;
    }

    // Getter Method - Get Transaction View
    public AnchorPane getTransactionsView() {
        if (transactionsView == null) {
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Transactions View not found: {0}", ex.getMessage());
            }
        }
        return transactionsView;
    }

    // Getter Method - Get Account View
    public AnchorPane getAccountsView() {
        if (accountsView == null) {
            try {
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Account View not found: {0}", ex.getMessage());
            }
        }
        return accountsView;
    }

    // Setter Method - Show Client Window
    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    /*===========================================
    # Admin View Methods
    ============================================*/
    // Getter Method - Get Admin Selected Menu Item
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    // Getter Method - Get Create Client View
    public AnchorPane getCreateClientView() {
        if (createClientView == null) {
            try {
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Create Client View not found: {0}", ex.getMessage());
            }
        }
        return createClientView;
    }

    // Getter Method - Get Clients View
    public AnchorPane getClientsView() {
        if (clientsView == null) {
            try {
                clientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Clients View not found: {0}", ex.getMessage());
            }
        }
        return clientsView;
    }

    // Setter Method - Show Admin Window
    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    /*===========================================
    # Login View Method
    ============================================*/
    // Setter/Mutator Method - Show Login Window
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    /*===========================================
    # Stage Methods
    ============================================*/
    // Setter/Mutator Method - Create Stage
    public void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Window not found: {0}", ex.getMessage());
        }
        Stage stage = new Stage();
        // Place the scene in the stage
        stage.setScene(scene);
        // Set the stage title
        stage.setTitle("CRDB - Bank Management System");
        // Set Application logo
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/logo/logo1.png"))));
        // Prevent/Allow users to resize the stage
        stage.setResizable(false);
        // Display the stage
        stage.show();
    }

    // Setter Method - Close stage
    public void closeStage(Stage stage) {
        stage.close();
    }

}
