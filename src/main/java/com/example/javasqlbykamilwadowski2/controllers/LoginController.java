package com.example.javasqlbykamilwadowski2.controllers;

import com.example.javasqlbykamilwadowski2.utils.DBUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button bt_exit;

    @FXML
    private Button bt_login;

    @FXML
    private Button bt_minimise;

    @FXML
    private Button bt_register;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_username;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bt_register.setOnAction(event -> DBUtils.changeScene(event, "/fxml/sign-up.fxml", "Sign-Up!", null, null, null, null, null, null));

        bt_exit.setOnAction(event -> Platform.exit());

        bt_minimise.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });

        bt_login.setOnAction(event -> DBUtils.logInUser(event, tf_username.getText(), pf_password.getText()));

    }
}
