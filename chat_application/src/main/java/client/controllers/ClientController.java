package client.controllers;

import client.models.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class ClientController {

    @FXML private TextArea zoneMessages;
    @FXML private TextField champMessage;

    private Client client;

    @FXML
    public void initialize() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Pseudo");
        dialog.setHeaderText("Entrez votre pseudo");
        dialog.setContentText("Pseudo : ");

        Optional<String> result = dialog.showAndWait();
        String pseudo = result.orElse("Utilisateur");

        try {
            client = new Client("localhost", 1234, pseudo);
            zoneMessages.appendText("✅ Connecté en tant que : " + pseudo + "\n");

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = client.getReader().readLine()) != null) {
                        String finalMsg = msg;
                        Platform.runLater(() -> zoneMessages.appendText(finalMsg + "\n"));
                    }
                } catch (IOException e) {
                    Platform.runLater(() -> zoneMessages.appendText("⚠️ Déconnexion du serveur.\n"));
                }
            }).start();

        } catch (IOException e) {
            zoneMessages.appendText("❌ Impossible de se connecter au serveur.\n");
            e.printStackTrace();
        }
    }

    @FXML
    void envoyerMessage() {
        String message = champMessage.getText().trim();
        if (!message.isEmpty()) {
            client.envoyerMessage(message);
            champMessage.clear();
        }
    }
}
