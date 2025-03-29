package com.tp.consultations.gestion_consultations.utils;

import com.tp.consultations.gestion_consultations.models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public static void ajouterPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (nom, prenom, dateNaissance, telephone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setDate(3, Date.valueOf(patient.getDateNaissance()));
            stmt.setString(4, patient.getTelephone());
            stmt.executeUpdate();
        }
    }

    public static void modifierPatient(Patient patient) throws SQLException {
        String sql = "UPDATE patients SET nom=?, prenom=?, dateNaissance=?, telephone=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setDate(3, Date.valueOf(patient.getDateNaissance()));
            stmt.setString(4, patient.getTelephone());
            stmt.setInt(5, patient.getId());
            stmt.executeUpdate();
        }
    }

    public static void supprimerPatient(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Patient> obtenirTousLesPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateNaissance").toLocalDate(),
                        rs.getString("telephone")
                ));
            }
        }
        return patients;
    }
}
