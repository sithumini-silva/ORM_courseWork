package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.ProgrammeBO;
import org.example.bo.custom.PatientBO;
import org.example.bo.custom.PatientProgrameBO;
import org.example.entity.Program;
import org.example.entity.Patient;
import org.example.models.ProgramDTO;
import org.example.models.RegisterDTO;
import org.example.models.PatientDTO;
import org.example.tdm.RegisterTm;


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PatientProgramController {

    @FXML
    private Button btnRegi;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbPrId;

    @FXML
    private ComboBox<String> cmbstId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPrFee;

    @FXML
    private TableColumn<?, ?> colPrId;

    @FXML
    private TableColumn<?, ?> colPrName;

    @FXML
    private TableColumn<?, ?> colReFee;

    @FXML
    private TableColumn<?, ?> colReId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private TableColumn<?, ?> colStName;

    @FXML
    private DatePicker datePicker;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<RegisterTm> tblRegisters;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtId;

    @FXML
    private TextField txtPrFee;

    @FXML
    private TextField txtPrName;

    @FXML
    private TextField txtRegiFee;

    @FXML
    private TextField txtStName;

    PatientProgrameBO patientProgrameBO = (PatientProgrameBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENTPROGRAME);
    PatientBO patientBO = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    ProgrammeBO programBO = (ProgrammeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMME);



    public void initialize() {
        generateRegiId();
        loadRegIds();
        getProgramIds();
        getPatientIds();
        addTableSelectionListener();
        setCellValueFactory();
        loadAllRegistrations();



//        cmbPrId.setOnAction(event -> fillProgrammeDetails());

//        colReId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
//        colStId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
//        colPrId.setCellValueFactory(new PropertyValueFactory<>("programId"));
//        colReFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
//        colPrFee.setCellValueFactory(new PropertyValueFactory<>("regiFee"));

    }





//    public void loadAllRegistrations() {
//        ObservableList<RegisterTm> obList = FXCollections.observableArrayList();
//        List<RegisterDTO> registerList = patientProgrameBO.getAll(); // Fetch all registrations
//
//        // Map RegisterDTO to RegisterTm for TableView
//        for (RegisterDTO registerDTO : registerList) {
//            RegisterTm registerTm = new RegisterTm(
//                    registerDTO.getRegister_id(),
//                    registerDTO.getDate(),
//                    registerDTO.getPatient(),
//                    registerDTO.getProgram(),
//                    registerDTO.getPatientName(),
//                    registerDTO.getProgramName(),
//                    registerDTO.getProgramFee(),
//                    registerDTO.getRegiFee()
//            );
//            obList.add(registerTm);
//        }
//
//        // Populate the TableView
//        tblRegisters.setItems(obList);
//    }

//    public void loadAllRegistrations() {
//        ObservableList<RegisterTm> obList = FXCollections.observableArrayList();
//        List<RegisterDTO> registerList = patientProgrameBO.getAll(); // Fetch all registrations
//
//        for (RegisterDTO registerDTO : registerList) {
//            RegisterTm registerTm = new RegisterTm(
//                    registerDTO.getRegister_id(),
//                    registerDTO.getDate(),
//                    registerDTO.getPatient().getId(),  // Get patient ID
//                    registerDTO.getProgram().getCode(), // Get program code
//                    registerDTO.getPatientName(),
//                    registerDTO.getProgramName(),
//                    registerDTO.getProgramFee(),
//                    registerDTO.getRegiFee()
//            );
//            obList.add(registerTm);
//        }
//
//        tblRegisters.setItems(obList); // Populate the TableView
//    }

    public void loadAllRegistrations() {
        ObservableList<RegisterTm> obList = FXCollections.observableArrayList();
        try {
            List<RegisterDTO> registerList = patientProgrameBO.getAll();
            if (registerList != null) {
                for (RegisterDTO registerDTO : registerList) {
                    if (registerDTO != null) {
                        RegisterTm registerTm = new RegisterTm(
                                registerDTO.getRegister_id() != null ? registerDTO.getRegister_id() : "N/A",
                                registerDTO.getDate() != null ? registerDTO.getDate() : "N/A",
                                (registerDTO.getPatient() != null && registerDTO.getPatient().getId() != null)
                                        ? registerDTO.getPatient().getId() : "N/A",
                                (registerDTO.getProgram() != null && registerDTO.getProgram().getCode() != null)
                                        ? registerDTO.getProgram().getCode() : "N/A",
                                registerDTO.getPatientName() != null ? registerDTO.getPatientName() : "Unknown Patient",
                                registerDTO.getProgramName() != null ? registerDTO.getProgramName() : "Unknown Program",
                                registerDTO.getProgramFee() != 0 ? registerDTO.getProgramFee() : 0.0,
                                registerDTO.getRegiFee() != 0 ? registerDTO.getRegiFee() : 0.0
                        );
                        obList.add(registerTm);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading registrations: " + e.getMessage());

        }
        tblRegisters.setItems(obList); // Populate the TableView
    }

    //    private void setCellValueFactory(){
//        colReId.setCellValueFactory(new PropertyValueFactory<>("register_id"));
//        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
//        colStId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
//        colPrId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
//        colStName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
//        colPrName.setCellValueFactory(new PropertyValueFactory<>("programName"));
//        colPrFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
//        colReFee.setCellValueFactory(new PropertyValueFactory<>("regiFee"));
//        System.out.println("done");
//    }
private void setCellValueFactory() {
    colReId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
    colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    colStId.setCellValueFactory(new PropertyValueFactory<>("patientId")); // Uses patientId field
    colPrId.setCellValueFactory(new PropertyValueFactory<>("programId")); // Uses programId field
    colStName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
    colPrName.setCellValueFactory(new PropertyValueFactory<>("programName"));
    colPrFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
    colReFee.setCellValueFactory(new PropertyValueFactory<>("regiFee"));
}

    private void addTableSelectionListener() {
        tblRegisters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getRegisterDetails(newValue);
            }
        });
    }
    private void getRegisterDetails(RegisterTm registrationTm) {
        txtId.setText(registrationTm.getRegisterId());
        txtDate.setText(registrationTm.getDate());
        cmbstId.setValue(String.valueOf(registrationTm.getPatientId()));
        cmbPrId.setValue(String.valueOf(registrationTm.getProgramId()));
        txtStName.setText(registrationTm.getPatientName());
        txtPrName.setText(registrationTm.getProgramName());
        txtPrFee.setText(String.valueOf(registrationTm.getProgramFee()));
        txtRegiFee.setText(String.valueOf(registrationTm.getRegiFee()));
    }

    private void getPatientIds() {
        List<PatientDTO> patientsList = patientBO.getAllPatients();

        for (PatientDTO patientDTO : patientsList){
            cmbstId.getItems().add(patientDTO.getId());
        }
    }

    private void getProgramIds() {
        List<ProgramDTO> programsList = programBO.getAllPrograms();

        for (ProgramDTO programDTO : programsList){
            cmbPrId.getItems().add(programDTO.getCode());
        }
    }

    @FXML
    void btnRegiOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String registerId = txtId.getText();
        String date = String.valueOf(Date.valueOf(LocalDate.now()));
        String patientId = cmbstId.getValue();
        String programId = cmbPrId.getValue();
        String patientName = txtStName.getText();
        String programName = txtPrName.getText();
        double programFee = Double.parseDouble(txtPrFee.getText());
        double upfrontPayment = Double.parseDouble(txtRegiFee.getText());

        PatientDTO patientDTO = patientBO.searchById(patientId);

        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setUser(patientDTO.getUser());
        patient.setName(patientDTO.getName());
        patient.setAddress(patientDTO.getAddress());
        patient.setEmail(patientDTO.getEmail());
        patient.setTel(patientDTO.getTel());

        ProgramDTO programDTO = programBO.searchById(programId);

        Program program = new Program();

        program.setCode(programId);
        program.setName(programDTO.getName());
        program.setDuration(programDTO.getDuration());
        program.setPrice(programDTO.getPrice());

        boolean isSaved = patientProgrameBO.saveRegistration(new RegisterDTO(registerId,date,patient,program,patientName,programName,programFee,upfrontPayment));

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Registration completed!").show();
            loadAllRegistrations();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Registration not completed!").show();
        }

    }

    @FXML
    void cmbPrNameOnAction(ActionEvent event) {
        String programId = cmbPrId.getValue();

        ProgramDTO programDTO = patientProgrameBO.searchProgram(programId);

        if(programDTO != null){
            txtPrName.setText(programDTO.getName());
            txtPrFee.setText(String.valueOf(programDTO.getPrice()));
        }
        txtRegiFee.requestFocus();
    }

    @FXML
    void cmbStIdOnAction(ActionEvent event) {
        String patientId = cmbstId.getValue();

        PatientDTO patientDTO = patientProgrameBO.searchPatient(patientId);

        if(patientDTO != null){
            txtStName.setText(patientDTO.getName());
        }
        txtRegiFee.requestFocus();
    }

    @FXML
    void txtDateOnAction(MouseEvent event) {

    }

    @FXML
    void txtIdOnAction(MouseEvent event) {

    }

    @FXML
    void txtPrFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtPrIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtRegiFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStNameOnAction(ActionEvent event) {

    }


    private void loadRegIds() {
        // You can implement logic to load existing registration IDs if needed
    }





    @FXML
    void DateOnAction(ActionEvent event) {
        LocalDate myDate = datePicker.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        txtDate.setText(myFormattedDate);
    }

    private String generateRegiId() {
        try {
            String currentId = patientProgrameBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("R00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "R00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("R001");
                return "R001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    private void clearFormFields() {
        cmbstId.setValue(null);
        txtStName.clear();
        cmbPrId.setValue(null);
        txtPrName.clear();
        txtPrFee.clear();
        txtRegiFee.clear();
    }

}

