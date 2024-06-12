package com.celebrate.crdb_bank.Models;

import com.celebrate.crdb_bank.Views.AccountType;
import com.celebrate.crdb_bank.Views.ViewFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/*===========================================
# Singleton design pattern implementation.

The Singleton design pattern is a creational pattern that ensures a class has only one instance,
and provides a global access point to it.

Purpose:
Controls object creation and guarantees a single instance.
Provides a global access point to that single instance.
Useful for scenarios like:
Logger classes
Configuration managers
Driver objects for resources (e.g., database connection pool)

Benefits:
Ensures a single instance throughout the application.
Provides a centralized access point for interacting with the object.

Drawbacks:
Can make testing more difficult (consider dependency injection for better testability).
Overuse can lead to tight coupling and reduced flexibility.

Alternatives:
Factory Pattern: Offers more flexibility in creating different object types.
Dependency Injection: Promotes loose coupling and easier testing.

When to Use:
Consider using Singleton when you need a single, globally accessible object for resource management, logging, or configuration.
However, evaluate if alternatives like dependency injection might be more suitable for your specific use case.
============================================*/
public class Model {
    // Static Instance: A private static member variable of the class is used to hold the single instance.
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.CLIENT;

    // Logger
    private static final Logger LOGGER = Logger.getLogger(DatabaseDriver.class.getName());


    /*===========================================
    # Client - Data
    ============================================*/
    private final Client client;
    private boolean clientLoginSuccessFlag;

    /*===========================================
    # Admin - Data
    ============================================*/

    // Constructor
    // Private Constructor: The constructor is declared private to prevent external object creation
    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
         /*===========================================
         # Client - Data
         ============================================*/
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);

        /*===========================================
         # Admin - Data
         ============================================*/

    }

    // Getter Method - Get Model Instance
    // Static Factory Method: A public static method provides the global access point to get the instance
    /*
     *  1. Checks if the instance already exists (using lazy initialization if needed).
     *  2. If not, creates the instance using the private constructor.
     *  3. Returns the instance.
     * */
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    // Getter Method - Get View Factory
    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    // Getter - Get Database Driver
    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    // Getter - Get Login Account Type
    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    // Setter - Set Login Account Type
    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*===========================================
    # Client - Methods
    ============================================*/
    // Setters
    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    public void evaluateClientCred(String pAddress, String password) throws SQLException {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet rs = databaseDriver.getClientData(pAddress, password);
        try {
            if (rs.isBeforeFirst()) {
                this.client.firstNameProperty().set(rs.getString("FirstName"));
                this.client.lastNameProperty().set(rs.getString("LastName"));
                this.client.payeeAddressProperty().set(rs.getString("PayeeAddress"));
                String[] dateParts = rs.getString("Date").split("-");
                // Converting database date format to local date format and also converting the string
                // data type of the date to integer
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                this.client.dateCreatedProperty().set(date);
                this.clientLoginSuccessFlag = true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing SQL query: {0}", e.getMessage());
            throw e;
        }
    }

    // Getters
    public boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;
    }

    public Client getClient() {
        return this.client;
    }

    /*===========================================
    # Admin - Methods
    ============================================*/
}
