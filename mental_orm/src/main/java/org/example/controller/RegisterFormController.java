package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.models.UserDTO;
import org.example.tdm.UserTm;
import org.example.util.PasswordUtil;
import org.example.util.Regex;
import org.example.util.TextFields;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

public class RegisterFormController {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colPass;
    @FXML
    private TableColumn<?, ?> colRole;
    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane rootNode;
    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtTel;

    private UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        setTable();
        setValueFactory();
        selectTableRow();
        generateUserId();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = userBO.delete(new UserDTO(txtId.getText(), txtName.getText(), txtRole.getText(),
                txtTel.getText(), txtEmail.getText(), txtPassword.getText()));
        if (isDeleted) {
            clearTextFields();
            setTable();
            setValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User deletion unsuccessful").show();
        }
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    void clearTextFields() {
        txtId.clear();
        txtName.clear();
        txtRole.clear();
        txtEmail.clear();
        txtTel.clear();
        txtPassword.clear();
    }

    private String generateUserId() {
        try {
            String currentId = userBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("U00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "U00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("U001");
                return "U001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    private void setTable() {
        ObservableList<UserTm> userTms = FXCollections.observableArrayList();
        List<UserDTO> all = userBO.getAll();
        for (UserDTO userDTO : all) {
            UserTm userTm = new UserTm(userDTO.getId(), userDTO.getName(), userDTO.getRole(), userDTO.getEmail(), userDTO.getTel(), userDTO.getPassword());
            userTms.add(userTm);
        }
        tblUser.setItems(userTms);
    }

    private void selectTableRow() {
        tblUser.setOnMouseClicked(event -> {
            int focusedIndex = tblUser.getFocusModel().getFocusedIndex();
            UserTm userTm = tblUser.getItems().get(focusedIndex);
            txtId.setText(userTm.getId());
            txtName.setText(userTm.getName());
            txtRole.setText(userTm.getRole());
            txtTel.setText(String.valueOf(userTm.getTel()));
            txtEmail.setText(userTm.getEmail());
            txtPassword.setText(userTm.getPassword());
        });
    }

    // Encrypt password using BCrypt before saving/updating
    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        // Hash the password before saving
        String hashedPassword = PasswordUtil.hashPassword(txtPassword.getText());
        boolean isSaved = userBO.save(new UserDTO(txtId.getText(), txtName.getText(), txtRole.getText(),
                txtTel.getText(), txtEmail.getText(), hashedPassword));
        if (isSaved) {
            clearTextFields();
            setTable();
            setValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User save unsuccessful").show();
        }
//        // Encrypt the password before saving
//        String hashedPassword = encryptPassword(txtPassword.getText());
//        boolean isSaved = userBO.save(new UserDTO(txtId.getText(), txtName.getText(), txtRole.getText(),
//                txtTel.getText(), txtEmail.getText(), hashedPassword));
//        if (isSaved) {
//            clearTextFields();
//            setTable();
//            setValueFactory();
//            tblUser.refresh();
//            txtId.setText(generateUserId());
//            new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "User save unsuccessful").show();
//        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        // Implement search functionality if required
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // Encrypt the password before updating
        String hashedPassword = encryptPassword(txtPassword.getText());
        boolean isUpdated = userBO.update(new UserDTO(txtId.getText(), txtName.getText(), txtRole.getText(),
                txtTel.getText(), txtEmail.getText(), hashedPassword));
        if (isUpdated) {
            clearTextFields();
            setTable();
            setValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User update unsuccessful").show();
        }
    }




    @FXML
    void txtRoleOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.EMAIL,txtEmail);
        txtTel.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

        txtRole.requestFocus();
    }

    @FXML
    void txtTelOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.Contact,txtTel);
        txtPassword.requestFocus();
    }


    public void txtUserIdOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFields.UserID,txtId);

    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }
}
