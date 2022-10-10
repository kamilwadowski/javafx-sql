package com.example.javasqlbykamilwadowski2.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedController implements Initializable {

    @FXML
    private Button bt_exit;

    @FXML
    private Button bt_minimise;

    @FXML
    private Label lb_birthdate;

    @FXML
    private Label lb_email;

    @FXML
    private Label lb_firstname;

    @FXML
    private Label lb_lastname;

    @FXML
    private Label lb_phoneNumber;

    @FXML
    private Label lb_username;

    @FXML
    private Label lb_welcome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bt_exit.setOnAction(event -> Platform.exit());

        bt_minimise.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });

    }

    public void setUserInformation(String firstname, String lastname, String email, String phoneNumber, String birthdate, String username) {
        lb_firstname.setText(firstname);
        lb_lastname.setText(lastname);
        lb_email.setText(email);
        lb_phoneNumber.setText(phoneNumber);
        lb_birthdate.setText(birthdate);
        lb_username.setText(username);
        lb_welcome.setText(firstname);
    }
}
