package com.celebrate.crdb_bank.Controllers;

import com.celebrate.crdb_bank.Models.Model;
import com.celebrate.crdb_bank.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;

    // Abstract Setter Method - Implementing polymorphism by overriding initialize method of abstract class Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(e -> onLogin());
    }

    // Setter Method - Set/Show Client Window on onLogin Button Click
    public void onLogin() {
        // Get Window Stage
        Stage stage = (Stage) login_btn.getScene().getWindow();
        if (acc_selector.getValue() == AccountType.CLIENT) {
            // Evaluate Client Login Credentials
            if (!payee_address_fld.getText().isEmpty() && !password_fld.getText().isEmpty()) {
                try {
                    Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
                    if (Model.getInstance().getClientLoginSuccessFlag()) {
                        // Show Client Window
                        Model.getInstance().getViewFactory().showClientWindow();
                        // Close stage
                        // A trick to close stage in Java FX since JavaFX does not have a built-in functionality
                        // for closing stage in transition between stages
                        Model.getInstance().getViewFactory().closeStage(stage);
                    } else {
                        payee_address_fld.setText("");
                        password_fld.setText("");
                        error_lbl.setText("No such Login Credentials. Please try again.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                error_lbl.setText("Please enter your credentials");
                error_lbl.setVisible(true);
            }

        } else {
            // Show Admin Window
            Model.getInstance().getViewFactory().showAdminWindow();
            // Close stage
            // A trick to close stage in Java FX since JavaFX does not have a built-in functionality
            // for closing stage in transition between stages
            Model.getInstance().getViewFactory().closeStage(stage);
        }

    }
}
