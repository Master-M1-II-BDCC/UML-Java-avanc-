package com.tp4.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tp4/javafx/views/product-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/com/tp4/javafx/styles/style.css").toExternalForm());

        primaryStage.setTitle("Gestion des Produits");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
