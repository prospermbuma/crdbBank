package com.celebrate.crdb_bank.Models;

import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Transaction implements Initializable {

    // Instance Properties
    public String sender;
    public String receiver;
    public Double amount;
    public LocalDate date;
    public String message;
    public Boolean isIncoming;

    // Logger
    private static final Logger LOGGER = Logger.getLogger(Transaction.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // Constructor
    public Transaction(String sender, String receiver, Double amount, LocalDate date, String message, boolean incoming) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
        this.message = message;
        this.isIncoming = incoming;
    }

    // Methods

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIncoming(Boolean isIncoming) {
        this.isIncoming = isIncoming;
    }

    public boolean isIncoming() {
        return isIncoming;
    }

    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public Double getAmount() {
        return this.amount;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getMessage() {
        return this.message;
    }

}
