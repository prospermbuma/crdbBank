package com.celebrate.crdb_bank.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionManager {
    private static SessionManager instance;
    private String loggedInClientPayeeAddress;
    private final String sessionDate;

    private SessionManager() {
        // Set session date to current date/time on instantiation
        this.sessionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String getLoggedInClientPayeeAddress() {
        return loggedInClientPayeeAddress;
    }

    public void setLoggedInClientPayeeAddress(String payeeAddress) {
        this.loggedInClientPayeeAddress = payeeAddress;
    }

    public String getSessionDate() {
        return sessionDate;
    }
}
