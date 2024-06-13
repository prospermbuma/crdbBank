package com.celebrate.crdb_bank.Controllers;

import com.celebrate.crdb_bank.Models.Model;
import com.celebrate.crdb_bank.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label username_lbl;
    public TextField username_fld;
    public PasswordField user_password_fld;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public PasswordField payee_password_fld;
    public Button login_btn;
    public Label error_lbl;


    // Abstract Setter Method - Implementing polymorphism by overriding initialize method of abstract class Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());

        // Add a listener to the ChoiceBox value property to update the UI when the selected account type changes
        acc_selector.valueProperty().addListener((observable, oldValue, newValue) -> updateUI(newValue));

        // Initial UI update based on the initial Choice value
        updateUI(acc_selector.getValue());

        login_btn.setOnAction(e -> onLogin());
    }

    private void updateUI(AccountType accountType) {
        if (accountType == AccountType.CLIENT) {
            // Set Username Visibility: False
            username_lbl.setVisible(false);
            username_fld.setVisible(false);
            user_password_fld.setVisible(false);
            // Set Username Managed Property: False
            username_lbl.setManaged(false);
            username_fld.setManaged(false);
            user_password_fld.setManaged(false);

            // Set Payee Address Visibility: True
            payee_address_lbl.setVisible(true);
            payee_address_fld.setVisible(true);
            payee_password_fld.setVisible(true);
            // Set Payee Address Managed Property: True
            payee_address_lbl.setManaged(true);
            payee_address_fld.setManaged(true);
            payee_password_fld.setManaged(true);

            // Clear field
            username_fld.setText("");
            user_password_fld.setText("");
        } else if (accountType == AccountType.ADMIN) {
            // Set Username Visibility: True
            username_lbl.setVisible(true);
            username_fld.setVisible(true);
            user_password_fld.setVisible(true);
            // Set Username Managed Property: True
            username_lbl.setManaged(true);
            username_fld.setManaged(true);
            user_password_fld.setManaged(true);

            // Set Payee Address Visibility: False
            payee_address_lbl.setVisible(false);
            payee_address_fld.setVisible(false);
            payee_password_fld.setVisible(false);
            // Set Payee Address Managed Property: False
            payee_address_lbl.setManaged(false);
            payee_address_fld.setManaged(false);
            payee_password_fld.setManaged(false);

            // Clear field
            payee_address_fld.setText("");
            payee_password_fld.setText("");
        }
    }

    // Setter Method - Set/Show Client OR Admin Window on onLogin Button Click
    public void onLogin() {
        // Get Window Stage
        Stage stage = (Stage) login_btn.getScene().getWindow();
        // Login as Client if account type is CLIENT
        if (acc_selector.getValue() == AccountType.CLIENT) {
            // Evaluate Client Login Credentials
            if (!payee_address_fld.getText().isEmpty() && !payee_password_fld.getText().isEmpty()) {
                try {
                    Model.getInstance().evaluateClientCred(payee_address_fld.getText(), payee_password_fld.getText());
                    if (Model.getInstance().getClientLoginSuccessFlag()) {
                        // Show Client Window
                        Model.getInstance().getViewFactory().showClientWindow();
                        // Close stage
                        // A trick to close stage in Java FX since JavaFX does not have a built-in functionality
                        // for closing stage in transition between stages
                        Model.getInstance().getViewFactory().closeStage(stage);
                    } else {
                        payee_address_fld.setText("");
                        payee_password_fld.setText("");
                        error_lbl.setText("No such Login Credentials. Please try again.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                error_lbl.setText("Please enter your credentials");
                error_lbl.setVisible(true);
            }

        }
        // Login as Admin if account type is ADMIN
        else if (acc_selector.getValue() == AccountType.ADMIN) {
            // Evaluate Admin Login Credentials
            if (!username_fld.getText().isEmpty() && !user_password_fld.getText().isEmpty()) {
                try {
                    Model.getInstance().evaluateAdminCred(username_fld.getText(), user_password_fld.getText());
                    if (Model.getInstance().getAdminLoginSuccessFlag()) {
                        // Show Admin Window
                        Model.getInstance().getViewFactory().showAdminWindow();
                        // Close stage
                        // A trick to close stage in Java FX since JavaFX does not have a built-in functionality
                        // for closing stage in transition between stages
                        Model.getInstance().getViewFactory().closeStage(stage);
                    } else {
                        username_fld.setText("");
                        user_password_fld.setText("");
                        error_lbl.setText("No such Login Credentials. Please try again.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                error_lbl.setText("Please enter your credentials");
                error_lbl.setVisible(true);
            }
        }

    }

}
