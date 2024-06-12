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
        // Show client window
        Model.getInstance().getViewFactory().showClientWindow();
        // A trick to close stage in Java FX since JavaFX does not have a built-in functionality
        // for closing stage in transition between stages
        Stage stage = (Stage) login_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
