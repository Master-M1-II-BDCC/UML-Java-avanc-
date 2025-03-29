module com.tp.consultations.gestion_consultations {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.tp.consultations.gestion_consultations to javafx.fxml;
    opens com.tp.consultations.gestion_consultations.controllers to javafx.fxml;

    exports com.tp.consultations.gestion_consultations;
    exports com.tp.consultations.gestion_consultations.controllers;
    exports com.tp.consultations.gestion_consultations.models;
}
