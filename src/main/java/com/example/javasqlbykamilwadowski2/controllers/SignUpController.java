package com.example.javasqlbykamilwadowski2.controllers;

import com.example.javasqlbykamilwadowski2.utils.DBUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button bt_back_to_login;

    @FXML
    private Button bt_exit;

    @FXML
    private Button bt_minimise;

    @FXML
    private Button bt_register;

    @FXML
    private DatePicker dp_birthdate;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_firstname;

    @FXML
    private TextField tf_lastname;

    @FXML
    private TextField tf_phone_number;

    @FXML
    private TextField tf_username;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bt_minimise.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });

        bt_exit.setOnAction(event -> Platform.exit());

        bt_back_to_login.setOnAction(event -> DBUtils.changeScene(event, "/fxml/login.fxml", "Log-In!", null, null, null, null, null, null));

        bt_register.setOnAction(event -> DBUtils.signUpUser(event, tf_firstname.getText(), tf_lastname.getText(), tf_email.getText(), tf_phone_number.getText(),
                String.valueOf(dp_birthdate.getValue()), tf_username.getText(), pf_password.getText()));

    }
}