package com.tp.consultations.gestion_consultations.controllers;

import com.tp.consultations.gestion_consultations.models.Patient;
import com.tp.consultations.gestion_consultations.utils.PatientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class PatientController {

    @FXML private TextField nomField, prenomField, telephoneField;
    @FXML private DatePicker dateNaissancePicker;
    @FXML private TableView<Patient> patientTable;

    @FXML private TableColumn<Patient, Integer> colId;
    @FXML private TableColumn<Patient, String> colNom;
    @FXML private TableColumn<Patient, String> colPrenom;
    @FXML private TableColumn<Patient, LocalDate> colDateNaissance;
    @FXML private TableColumn<Patient, String> colTelephone;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        afficherPatients();

        patientTable.getSelectionModel().selectedItemProperty().addListener((obs, ancienPatient, nouveauPatient) -> {
            if (nouveauPatient != null) {
                nomField.setText(nouveauPatient.getNom());
                prenomField.setText(nouveauPatient.getPrenom());
                dateNaissancePicker.setValue(nouveauPatient.getDateNaissance());
                telephoneField.setText(nouveauPatient.getTelephone());
            }
        });
    }


    private void afficherPatients() {
        try {
            patientTable.getItems().setAll(PatientDAO.obtenirTousLesPatients());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ajouterPatient() {
        try {
            Patient patient = new Patient(0, nomField.getText(), prenomField.getText(),
                    dateNaissancePicker.getValue(), telephoneField.getText());
            PatientDAO.ajouterPatient(patient);
            afficherPatients();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modifierPatient() {
        try {
            Patient selected = patientTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setNom(nomField.getText());
                selected.setPrenom(prenomField.getText());
                selected.setDateNaissance(dateNaissancePicker.getValue());
                selected.setTelephone(telephoneField.getText());
                PatientDAO.modifierPatient(selected);
                afficherPatients();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void supprimerPatient() {
        try {
            Patient selected = patientTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                PatientDAO.supprimerPatient(selected.getId());
                afficherPatients();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
