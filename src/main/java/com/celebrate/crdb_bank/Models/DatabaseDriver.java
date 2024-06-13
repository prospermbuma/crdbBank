package com.celebrate.crdb_bank.Models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseDriver {
    // Instance Variable
    private Connection conn;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(DatabaseDriver.class.getName());

    // Constructor
    public DatabaseDriver() {
        try {
            String url = "jdbc:sqlite:crdb.db";
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to connect to the database: {0}", e.getMessage());
        }
    }

    /*===========================================
    # Client - Methods only for Client
    ============================================*/
    // LOGIN
    public ResultSet getClientData(String pAddress, String password) throws SQLException {
        PreparedStatement stmt;
        ResultSet rs;
        try {
            stmt = this.conn.prepareStatement("SELECT * FROM Clients WHERE PayeeAddress = ? AND Password = ?");
            stmt.setString(1, pAddress);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing SQL query: {0}", e.getMessage());
            throw e;
        }
        return rs;
    }

    /*===========================================
    # Admin - Methods only for Admin
    ============================================*/
    // LOGIN
    public ResultSet getAdminData(String username, String password) throws SQLException {
        PreparedStatement stmt;
        ResultSet rs;
        try {
            stmt = this.conn.prepareStatement("SELECT * FROM Admins WHERE Username = ? AND Password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing SQL query: {0}", e.getMessage());
            throw e;
        }
        return rs;
    }

    /*===========================================
    # Utility Methods - Methods for both
    ============================================*/

    public Connection getConn() {
        return conn;
    }
}
