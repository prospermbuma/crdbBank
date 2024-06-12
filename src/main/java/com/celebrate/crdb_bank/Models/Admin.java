package com.celebrate.crdb_bank.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
    // Instance Variables
    private final StringProperty username;
    private final StringProperty password;

    // Constructor
    public Admin(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    // Getters
    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
