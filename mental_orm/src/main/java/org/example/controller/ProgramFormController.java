package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.ProgrammeBO;
import org.example.models.ProgramDTO;
import org.example.tdm.ProgramTm;
import org.example.util.Regex;
import org.example.util.TextFields;


import java.util.List;

public class ProgramFormController {

    @FXML
    private AnchorPane rootNode;


    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private TableView<ProgramTm> tblProgramme;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProgramme;


    ProgrammeBO programmeBO = (ProgrammeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMME);

    public void initialize() {
        setItemTable();
        setCellValueFactory();
        tableSelection();
        generateItemId();
    }

    void clearFields() {
        txtId.clear();
        txtProgramme.clear();
        txtPrice.clear();
        txtDuration.clear();
    }

    private String generateItemId() {
        try {
            String currentId = programmeBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("P00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "P00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("P001");
                return "P001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void tableSelection() {
        tblProgramme.setOnMouseClicked(event -> {
            int index = tblProgramme.getSelectionModel().getSelectedIndex();
            txtId.setText(String.valueOf(clmId.getCellData(index)));
            txtProgramme.setText(String.valueOf(clmName.getCellData(index)));
            txtPrice.setText(String.valueOf(clmPrice.getCellData(index)));
            txtDuration.setText(String.valueOf(clmDuration.getCellData(index)));
        });
    }

    private void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("code"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    private void setItemTable() {
        ObservableList<ProgramTm> obList = FXCollections.observableArrayList();
        List<ProgramDTO> items = programmeBO.getAll();
        for (ProgramDTO programDTO : items) {
            ProgramTm itemTm = new ProgramTm(
                    programDTO.getCode(),
                    programDTO.getName(),
                    programDTO.getPrice(),
                    programDTO.getDuration()
            );
            obList.add(itemTm);
        }
        tblProgramme.setItems(obList);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = programmeBO.delete(new ProgramDTO(txtId.getText(), txtProgramme.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isDeleted) {
            clearFields();
            setItemTable();
            setCellValueFactory();
            tblProgramme.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Program deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "program deleted unsuccessfully");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isSaved = programmeBO.save(new ProgramDTO(txtId.getText(), txtProgramme.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isSaved) {
            clearFields();
            txtId.setText(generateItemId());
            setItemTable();
            setCellValueFactory();
            tblProgramme.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Program saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Program saved unsuccessfully");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isUpdated = programmeBO.update(new ProgramDTO(txtId.getText(), txtProgramme.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isUpdated) {
            clearFields();
            setItemTable();
            setCellValueFactory();
            tblProgramme.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Program updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Program updated unsuccessfully");
        }
    }

    @FXML
    void txtDurationOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.ProgramID,txtId);
        txtProgramme.requestFocus();
    }

    @FXML
    void txtItemOnAction(ActionEvent event) {

    }

    @FXML
    void txtPriceOnAction(ActionEvent event) {
        Regex.setTextColor(TextFields.DOUBLE,txtPrice);
        txtDuration.requestFocus();

    }

}
