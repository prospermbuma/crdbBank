package com.celebrate.crdb_bank.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {

    // Instance Properties
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    // Constructor
    public Client(String firstName, String lastName, String payeeAddress, Account checkingAccount, Account savingsAccount, LocalDate dateCreated) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.payeeAddress = new SimpleStringProperty(payeeAddress);
        this.checkingAccount = new SimpleObjectProperty<>(checkingAccount);
        this.savingsAccount = new SimpleObjectProperty<>(savingsAccount);
        this.dateCreated = new SimpleObjectProperty<>(dateCreated);
    }

    // Getters
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty payeeAddressProperty() {
        return payeeAddress;
    }

    public ObjectProperty<Account> checkingAccountProperty() {
        return checkingAccount;
    }

    public ObjectProperty<Account> savingsAccountProperty() {
        return savingsAccount;
    }

    public ObjectProperty<LocalDate> dateCreatedProperty() {
        return dateCreated;
    }

}
