package com.celebrate.crdb_bank.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin extends User {

    // Constructor
    public Admin(String username, String password) {
        super(username, password);
    }

    // Getters
    public StringProperty usernameProperty() {
        return new SimpleStringProperty(this, "username");
    }

    public StringProperty passwordProperty() {
        return new SimpleStringProperty(this, "password");
    }

}
