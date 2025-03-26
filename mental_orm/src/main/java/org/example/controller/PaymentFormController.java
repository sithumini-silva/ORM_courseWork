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
import org.example.bo.custom.PaymentBO;
import org.example.bo.custom.ProgrammeBO;
import org.example.bo.custom.PatientBO;
import org.example.bo.custom.PatientProgrameBO;
import org.example.models.PaymentDTO;
import org.example.models.ProgramDTO;
import org.example.models.RegisterDTO;
import org.example.models.PatientDTO;
import org.example.tdm.PaymentTm;


import java.util.List;

public class PaymentFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbProId; // ComboBox for Programme IDs
    @FXML
    private ComboBox<String> cmbStId;  // ComboBox for patient IDs

    @FXML
    private ComboBox<String> cmbRegiId;

    @FXML
    private TableColumn<?, ?> colFullPay;

    @FXML
    private TableColumn<?, ?> colLast;

    @FXML
    private TableColumn<?, ?> colPaid;

    @FXML
    private TableColumn<?, ?> colPayId;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colStuId;

    @FXML
    private Label lblFullPay;

    @FXML
    private Label lblPayment;

    @FXML
    private Label txtPaymentId;

    @FXML
    private Label lblProName;

    @FXML
    private Label lblStName;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtPaid;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
        PatientBO patientBO = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    ProgrammeBO programmeBO = (ProgrammeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMME);

    PatientProgrameBO patientProgrameBO = (PatientProgrameBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENTPROGRAME);

    public void initialize() {
        setTable();
        setValueFactory();
        generatePaymentId();
        loadComboBoxes();
        selectTableRow();
        loadAllPayments();



        cmbStId.setOnAction(event -> fillPatientDetails());
        cmbProId.setOnAction(event -> fillProgrammeDetails());

    }

    private void loadAllPayments() {
        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();
        try {
            // Fetch all payment records from the database using the PaymentBO
            List<PaymentDTO> allPayments = paymentBO.getAll();

            // Map each PaymentDTO to a PaymentTm and add it to the ObservableList
            for (PaymentDTO paymentDTO : allPayments) {
                PaymentTm paymentTm = new PaymentTm(
                        paymentDTO.getId(),                    // Payment ID
                        paymentDTO.getFee(),                  // Total fee
                        paymentDTO.getRegisterFee(),          // Registration fee
                        paymentDTO.getTotalFee()              // Total payment
                );
                paymentTms.add(paymentTm);
            }

            // Populate the TableView with the list of PaymentTm objects
            tblPayment.setItems(paymentTms);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load payments. Please try again.").show();
        }
    }
    private void loadComboBoxes() {
        try {
            List<PatientDTO> patientList = patientBO.getAll();
            ObservableList<String> patientIds = FXCollections.observableArrayList();
            for (PatientDTO patient : patientList) {
                patientIds.add(patient.getId());
            }
            cmbStId.setItems(patientIds);


            List<ProgramDTO> programmeList = programmeBO.getAll();
            ObservableList<String> programmeIds = FXCollections.observableArrayList();
            for (ProgramDTO programme : programmeList) {
                programmeIds.add(programme.getCode());
            }
            cmbProId.setItems(programmeIds);


            List<RegisterDTO> registerList = patientProgrameBO.getAll();
            ObservableList<String> registerIds = FXCollections.observableArrayList();
            for (RegisterDTO register : registerList) {
                registerIds.add(register.getRegister_id());
            }
            cmbRegiId.setItems(registerIds);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading ComboBoxes").show();
        }
    }

    private void setValueFactory() {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullPay.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colPaid.setCellValueFactory(new PropertyValueFactory<>("registerFee"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("totalFee"));
    }

    private String generatePaymentId() {
        try {
            String currentId = paymentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("P00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "P00" + ++idNum;
                txtPaymentId.setText(availableId);
                return availableId;
            } else {
                txtPaymentId.setText("P001");
                return "P001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setTable() {
        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();
        List<PaymentDTO> all = paymentBO.getAll();
        for (PaymentDTO paymentDTO : all) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDTO.getId(),
                    paymentDTO.getFee(),
                    paymentDTO.getRegisterFee(),
                    paymentDTO.getTotalFee()
            );
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        calculateFullPayment();

        String patientId = cmbStId.getValue();
        String programmeId = cmbProId.getValue();

        String paymetId = txtPaymentId.getText();
        double fee = Double.valueOf(lblPayment.getText());
        double total= Double.parseDouble(lblFullPay.getText());
        double regi= Double.parseDouble(txtPaid.getText());
        String registerId = cmbRegiId.getValue();


        if (patientId == null || programmeId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select both Patient and Programme").show();
            return;
        }

        RegisterDTO register = new RegisterDTO(registerId);
        System.out.println(register);

        boolean isSaved = paymentBO.save(new PaymentDTO(paymetId,fee,total,regi,register));

        if (isSaved) {
            txtPaymentId.setText(generatePaymentId());
            setTable();
            setValueFactory();
            tblPayment.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment saved unsuccessfully").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String patientId = cmbStId.getValue();
        String programmeId = cmbProId.getValue();
        if (patientId == null || programmeId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select both Patient and Programme").show();
            return;
        }

        boolean isDeleted = paymentBO.delete(new PaymentDTO(
                txtPaymentId.getText(),
                Double.parseDouble(txtPaid.getText()),
                Double.parseDouble(lblPayment.getText()),
                Double.parseDouble(lblFullPay.getText())
        ));

        if (isDeleted) {
            setTable();
            setValueFactory();
            tblPayment.refresh();
            txtPaymentId.setText(generatePaymentId());
            new Alert(Alert.AlertType.CONFIRMATION, "Payment deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment deletion unsuccessful").show();
        }
    }



//    private double tryParseDouble(String value) {
//        try {
//            return Double.parseDouble(value);  // Try parsing as Double
//        } catch (NumberFormatException e) {
//            return -1;  // Return -1 if the parsing fails
//        }
//    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


    private void fillPatientDetails() {
            String selectedPatientId = cmbStId.getValue();
            if (selectedPatientId != null) {
                try {

                    PatientDTO patient = patientBO.get(selectedPatientId);
                    if (patient != null) {
                        lblStName.setText(patient.getName());
//                        lblAmount.setText(String.valueOf(patient.getPayment()));
                    } else {
                        lblStName.setText("Patient not found");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    lblStName.setText("Error fetching patient");
                }
            } else {
                lblStName.setText("");
            }
    }


    private void fillProgrammeDetails() {
        String selectedProgrammeId = cmbProId.getValue();
        if (selectedProgrammeId != null) {
            try {
                ProgramDTO programme = programmeBO.get(selectedProgrammeId);
                if (programme != null) {
                    lblProName.setText(programme.getName());
                    lblPayment.setText(String.valueOf(programme.getPrice()));
                } else {
                    lblProName.setText("Programme not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                lblProName.setText("Error fetching programme");
            }
        } else {
            lblProName.setText("");
        }
    }

    @FXML
    void cmbRegiIdOnAction(ActionEvent event) {

    }

    public void cmbProIdOnAction(ActionEvent actionEvent) {
    }

    public void cmbStIdOnAction(ActionEvent actionEvent) {
    }

    @FXML
    void txtPaidOnAction(ActionEvent event) {

    }

    public void lblPaymentOnAction(MouseEvent mouseEvent) {
    }

    public void lblAmountOnAction(MouseEvent mouseEvent) {
    }

    public void lblFullPayOnAction(MouseEvent mouseEvent) {
    }

    public void lblPaymentIdOnAction(MouseEvent mouseEvent) {
    }

    public void lblProNameOnAction(MouseEvent mouseEvent) {
    }

    public void lblStNameOnAction(MouseEvent mouseEvent) {
    }
    public void calculateFullPayment() {
        try {

            String paymentText = lblPayment.getText().trim();
            String amountText = txtPaid.getText().trim();


            if (paymentText.isEmpty() || amountText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Payment and Amount fields cannot be empty").show();
                return;
            }


            double payment = tryParseDouble(paymentText);
            double amount = tryParseDouble(amountText);


            if (payment == -1 || amount == -1) {
                new Alert(Alert.AlertType.ERROR, "Invalid number format in Payment or Amount").show();
                return;
            }


            double fullPayment = payment - amount;


            lblFullPay.setText(String.format("%.2f", fullPayment));

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred while calculating the full payment").show();
        }
    }


    private double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);  // Try parsing as Double
        } catch (NumberFormatException e) {
            return -1;  // Return -1 if the parsing fails
        }
    }

    private void selectTableRow() {
        tblPayment.setOnMouseClicked(event -> {

            int focusedIndex = tblPayment.getFocusModel().getFocusedIndex();
            PaymentTm paymentTm = tblPayment.getItems().get(focusedIndex);
            txtPaymentId.setText(paymentTm.getId());
            lblPayment.setText(String.valueOf(paymentTm.getFee()));
            txtPaid.setText(String.valueOf(paymentTm.getRegisterFee()));
            lblFullPay.setText(String.valueOf(paymentTm.getTotalFee()));


        });
    }

}
