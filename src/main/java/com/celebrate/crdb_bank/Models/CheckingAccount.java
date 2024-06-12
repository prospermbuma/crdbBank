package com.celebrate.crdb_bank.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

// Inheritance
public class CheckingAccount extends Account{
    // Instance Property
    // The number of transactions a client is allowed to do per day
    private final IntegerProperty transactionLimit;

    // Constructor
    public CheckingAccount(String owner, String accountNumber, double balance, int transactionLimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(transactionLimit);
    }

    // Getter
    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }
}
