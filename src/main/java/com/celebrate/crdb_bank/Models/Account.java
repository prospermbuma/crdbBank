package com.celebrate.crdb_bank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {
    // Instance Properties
    private final StringProperty owner;
    private final StringProperty accountNumber;
    private final DoubleProperty balance;

    // Constructor
    public Account(String owner, String accountNumber, double balance) {
        this.owner = new SimpleStringProperty(owner);
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.balance = new SimpleDoubleProperty(balance);
    }

    // Getters
    public StringProperty ownerProperty() {
        return owner;
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

}
