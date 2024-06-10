module com.celebrate.crdb_bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;


    opens com.celebrate.crdb_bank to javafx.fxml;
    // Exporting packages
    exports com.celebrate.crdb_bank;
    exports com.celebrate.crdb_bank.Controllers;
    exports com.celebrate.crdb_bank.Controllers.Admin;
    exports com.celebrate.crdb_bank.Controllers.Client;
    exports com.celebrate.crdb_bank.Models;
    exports com.celebrate.crdb_bank.Views;
}