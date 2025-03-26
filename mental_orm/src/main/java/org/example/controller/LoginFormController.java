package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.models.UserDTO;


import java.io.IOException;
import java.sql.SQLException;


public class LoginFormController {
@FXML
private Button btnLogin;

@FXML
private Button btnRegister;

@FXML
private TextField txtPassword;

@FXML
private TextField txtUserID;
@FXML
private AnchorPane rootNode;
@FXML
private TextField txtRoll;


    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
//    String userID = txtUserID.getText();
//    String password = txtPassword.getText();
//    String roll=txtRoll.getText();
//        // Get the stored hashed password from the database
//        String storedHash = userBO.getPasswordHashByUserId(userID);
//
//        // Check if the entered password matches the stored hash
//        if (PasswordUtil.checkPassword(password, storedHash)) {
//            // Login successful
//            new Alert(Alert.AlertType.CONFIRMATION, "Login successful").show();
//            // Redirect to the main page, for example
//        } else {
//            // Login failed
//            new Alert(Alert.AlertType.ERROR, "Invalid email or password").show();
//        }
//
//    try {
//        checkCredential(userID, password,roll);
//    } catch (SQLException e) {
//        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//    }
//}
        String userID = txtUserID.getText();
        String password = txtPassword.getText();
        String role = txtRoll.getText();

        try {
            boolean isAuthenticated = userBO.checkCredentials(userID, password);

            if (isAuthenticated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Login successful").show();
                checkCredential(userID,password,role);
//                navigateToTheDashboard();

            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid userID or password").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

        private void checkCredential(String userId, String password, String roll) throws SQLException, IOException, ClassNotFoundException {
        UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
        UserDTO user = userBO.getUsersIdAndPasswordAndRole(userId,roll);
        if (user != null) {
            if (user.getRole().toLowerCase().equals("receptionist")||user.getRole().toLowerCase().equals("Receptionist")) {
                navigateToTheDashboard();
            }
            else if (user.getRole().toLowerCase().equals("admin")||user.getRole().toLowerCase().equals("Admin")) {
                navigateToTheAdminDashboard();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Invalid role!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid credentials! Please try again.").show();
        }
    }



    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private void navigateToTheAdminDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/admin_dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }


    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/regi.fxml"));
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        Scene scene = new Scene(rootNode);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.show();
    }






    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

    void txtPassword(ActionEvent event){

    }

    public void txtPasswordOnAction(MouseEvent mouseEvent) {
        txtRoll.requestFocus();
    }
    @FXML
    void txtRollOnAction(ActionEvent event) {

    }

    public void hypForgetOnAction(ActionEvent actionEvent) throws IOException {
        navigateToThePasswordChangeForm();
    }

    private void navigateToThePasswordChangeForm() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Passwordchange_form.fxml"));
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        Scene scene = new Scene(rootNode);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Change Password Form");
        stage.show();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {

    txtPassword.requestFocus();
    }
}



