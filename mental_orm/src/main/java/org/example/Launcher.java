package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
//        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"))));
//        stage.setTitle("Login Form");
//        stage.centerOnScreen();
//        stage.show();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();
    }
}