package com.celebrate.crdb_bank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingsAccount extends Account {
    // The withdrawal limit from the savings
    private final DoubleProperty withdrawalLimit;

    // Constructor
    public SavingsAccount(String owner, String accountNumber, double balance, double withdrawalLimit) {
        super(owner, accountNumber, balance);
        this.withdrawalLimit = new SimpleDoubleProperty(withdrawalLimit);
    }

    // Getter
    public DoubleProperty withdrawalLimitProperty() {
        return withdrawalLimit;
    }
}
