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
import org.example.bo.custom.PatientBO;
import org.example.bo.custom.UserBO;
import org.example.entity.User;
import org.example.models.PatientDTO;
import org.example.models.UserDTO;
import org.example.tdm.PatientTm;
import org.example.util.Regex;
import org.example.util.TextFields;


import java.io.IOException;
import java.util.List;

public class PatientFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEnter;


    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExit;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUserId;
    @FXML
    private AnchorPane rootNode;

    @FXML
    private ComboBox<String> cmbUserId;
    @FXML
    private TableView<PatientTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtPay;



    PatientBO patientBO = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        setTable();
        setValueFactory();
        selectTableRow();
        addTableSelectionListener();
        generateCustomerId();
        loadUserIds();

    }




    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

    }

    void clearTextFields(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtTel.clear();

//        cmbUserId.clear();
    }

    private void getCurrentStId() {

    }
    private void loadUserIds(){
        ObservableList<String> userIds = FXCollections.observableArrayList();
        List<UserDTO> allUsers = userBO.getAll();
        for (UserDTO userDto : allUsers) {
            userIds.add(userDto.getId());
        }
        cmbUserId.setItems(userIds);
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage=(Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = patientBO.delete(new PatientDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtTel.getText(), txtEmail.getText()));
        if (isDeleted){
            clearTextFields();
            setTable();
            setValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Patient delete successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Patient delete unsuccessfully").show();
        }
    }



    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage=(Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnSavetOnAction(ActionEvent event) {
        String patientID = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtTel.getText();
        String userId = cmbUserId.getValue();

        UserDTO userDTO = userBO.searchByID(userId);

        User user = new User();

        user.setUserId(userId);
        user.setRole(userDTO.getRole());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setTel(userDTO.getTel());



        try {

            boolean isSaved = patientBO.savePatient(new PatientDTO(patientID, user, name, address, email, contact));

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Patient saved!").show();
                    loadAllPatients();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Patient not saved!").show();
                }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        }

    private void addTableSelectionListener() {
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getPatientDetails(newValue);
            }
        });
    }


    private void getPatientDetails(PatientTm patientTm) {
        txtId.setText(patientTm.getId());
        txtName.setText(patientTm.getName());
        txtAddress.setText(patientTm.getAddress());
        txtEmail.setText(patientTm.getEmail());
        txtTel.setText(patientTm.getTel());
        cmbUserId.setValue(String.valueOf(patientTm.getUser_id())); // Assuming cmbUser holds user IDs
    }


//load data into the table
    private void loadAllPatients(){
        ObservableList<PatientTm> obList = FXCollections.observableArrayList();

        try {
            List<PatientDTO> patientList = patientBO.getAllPatients();
            for(PatientDTO patientDTO : patientList){

                PatientTm patientTm = new PatientTm(
                        patientDTO.getId(),
                        patientDTO.getName(),
                        patientDTO.getAddress(),
                        patientDTO.getEmail(),
                        patientDTO.getTel()
                );

                obList.add(patientTm);
            }
            System.out.println("done1");
            tblCustomer.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading patient: " + e.getMessage()).show();
        }

    }


    private String generateCustomerId() {
        try {
            String currentId = patientBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("C00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "C00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("C001");
                return "C001";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



    private void setTable() {
        ObservableList<PatientTm> patientTms = FXCollections.observableArrayList();
        List<PatientDTO> all = patientBO.getAll();
        for (PatientDTO patientDTO : all) {
            PatientTm patientTm = new PatientTm(
                    patientDTO.getId(),
                    patientDTO.getName(),
                    patientDTO.getAddress(),
                    patientDTO.getEmail(),
                    patientDTO.getTel()
            );
            patientTms.add(patientTm);
        }

        tblCustomer.setItems(patientTms);
    }

    private void selectTableRow() {
        tblCustomer.setOnMouseClicked(event -> {
            int focusedIndex = tblCustomer.getFocusModel().getFocusedIndex();
            PatientTm patientTm = (PatientTm) tblCustomer.getItems().get(focusedIndex);
            txtId.setText(patientTm.getId());
            txtName.setText(patientTm.getName());
            txtAddress.setText(patientTm.getAddress());
            txtTel.setText(String.valueOf(patientTm.getTel()));
            txtEmail.setText(patientTm.getEmail());
        });
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isUpdated = patientBO.update(new PatientDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), (txtTel.getText()), txtEmail.getText()));
        if (isUpdated){
            clearTextFields();
            setTable();
            setValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Customer update successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer update unsuccessfully").show();
        }
    }




    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.EMAIL,txtEmail);
    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.PatientID,txtId);
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.Name,txtName);
        txtTel.requestFocus();
    }

    @FXML
    void txtTelOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.Contact,txtTel);
        txtAddress.requestFocus();

    }


}
