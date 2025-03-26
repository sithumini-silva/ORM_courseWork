package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CorDashboardFormController {
    @FXML
    private Button btnBack1;

    @FXML
    private Button btnDash;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnProgramme;

    @FXML
    private Button btnUser;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnRegi;

    @FXML
    private Button btnPatient;

    @FXML
    private Button btnPayment;

    @FXML
    private AnchorPane miniDash;

    @FXML
    private AnchorPane rootNode;



    @FXML
    void btnBack1OnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage=(Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnDashOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage=(Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnLogOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage=(Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnProgramOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/programForm.fxml"));
            miniDash.getChildren().clear();
            miniDash.getChildren().add(rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        try {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml"));
            miniDash.getChildren().clear();
            miniDash.getChildren().add(rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegiOnAction(ActionEvent event) throws IOException {
//        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/view/registerForm.fxml"));
//        Stage stage=(Stage) rootNode.getScene().getWindow();
//
//        stage.setScene(new Scene(anchorPane));
//        stage.setTitle("Login Form");
//        stage.centerOnScreen();
//        try {
//            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/registerForm.fxml"));
//            miniDash.getChildren().clear();
//            miniDash.getChildren().add(rootNode);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @FXML
    void btnStuOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Patient_form.fxml"));
            miniDash.getChildren().clear();
            miniDash.getChildren().add(rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
//        try {
//            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_form.fxml"));
//            miniDash.getChildren().clear();
//            miniDash.getChildren().add(rootNode);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/registrationform.fxml"));
            miniDash.getChildren().clear();
            miniDash.getChildren().add(rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
