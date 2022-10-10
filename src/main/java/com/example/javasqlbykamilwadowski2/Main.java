package com.example.javasqlbykamilwadowski2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/login.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        // text added
    }

    public static void main(String[] args) {
        launch();
    }
}