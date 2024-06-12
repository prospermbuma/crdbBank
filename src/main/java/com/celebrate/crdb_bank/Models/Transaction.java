package com.celebrate.crdb_bank.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transaction {

    // Instance Properties
    private final StringProperty sender;
    private final StringProperty receiver;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;

    // Constructor
    public Transaction(String sender, String receiver, Double amount, LocalDate date, String message) {
        this.sender = new SimpleStringProperty(sender);
        this.receiver = new SimpleStringProperty(receiver);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<>(date);
        this.message = new SimpleStringProperty(message);
    }

    // Getters
    public StringProperty senderProperty() {
        return this.sender;
    }

    public StringProperty receiverProperty() {
        return this.receiver;
    }

    public DoubleProperty amountProperty() {
        return this.amount;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    public StringProperty messageProperty() {
        return this.message;
    }

}
