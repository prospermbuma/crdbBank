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
    private final Boolean isIncoming;

    // Constructor
    public Transaction(String sender, String receiver, Double amount, LocalDate date, String message, boolean incoming) {
        this.sender = new SimpleStringProperty(sender);
        this.receiver = new SimpleStringProperty(receiver);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<>(date);
        this.message = new SimpleStringProperty(message);
        this.isIncoming = incoming;
    }

    // Methods
    public boolean isIncoming() {
        return isIncoming;
    }

    public StringProperty getSender() {
        return this.sender;
    }

    public StringProperty getReceiver() {
        return this.receiver;
    }

    public DoubleProperty getAmount() {
        return this.amount;
    }

    public ObjectProperty<LocalDate> getDate() {
        return this.date;
    }

    public StringProperty getMessage() {
        return this.message;
    }

}
