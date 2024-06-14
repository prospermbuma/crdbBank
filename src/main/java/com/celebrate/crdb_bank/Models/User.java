package com.celebrate.crdb_bank.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    // Encapsulation
    // Instance Variables
    protected final StringProperty username;
    protected final StringProperty password;

    // Constructor
    public User(String username, String password) {
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
