package com.example.javasqlbykamilwadowski2.utils;

import com.example.javasqlbykamilwadowski2.controllers.LoggedController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String firstname, String lastname,
                                   String username, String email, String phoneNumber, String birthdate) {
        Parent root = null;
        if(firstname != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedController loggedController;
                loggedController = loader.getController();
                loggedController.setUserInformation(firstname, lastname, email, phoneNumber, birthdate, username);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            assert root != null;
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle(title);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void signUpUser(ActionEvent event, String firstname, String lastname, String email, String phoneNumber,
                                  String birthdate, String username, String password) {
        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-sql", "root", "Demethor1973");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND email = ?");
            psCheckUserExists.setString(1, username);
            psCheckUserExists.setString(2, email);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("User already exists!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users(firstname, lastname, email, phone_number, birthdate, username, password) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, firstname);
                psInsert.setString(2, lastname);
                psInsert.setString(3, email);
                psInsert.setString(4, phoneNumber);
                psInsert.setString(5, birthdate);
                psInsert.setString(6, username);
                psInsert.setString(7, password);
                psInsert.executeUpdate();

                changeScene(event, "/fxml/logged.fxml", "Welcome!", firstname, lastname, username, email, phoneNumber, birthdate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psInsert != null) {
                try {
                   psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-sql", "root", "Demethor1973");
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("User not found!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedFirstname = resultSet.getString("firstname");
                    String retrievedLastname = resultSet.getString("lastname");
                    String retrievedEmail = resultSet.getString("email");
                    String retrievedBirthdate = resultSet.getString("birthdate");
                    String retrievedPhoneNumber = resultSet.getString("phone_number");

                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "/fxml/logged.fxml", "Welcome", retrievedFirstname, retrievedLastname, username,
                                retrievedEmail, retrievedPhoneNumber, retrievedBirthdate);
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
