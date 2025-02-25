package com.tp4.javafx.controllers;

import com.tp4.javafx.models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private void addProduct() {
        String name = nameField.getText();
        String priceText = priceField.getText();

        if (name.isEmpty() || priceText.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            Product product = new Product(name, price);
            productListView.getItems().add(product);
            nameField.clear();
            priceField.clear();
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Le prix doit être un nombre valide.");
        }
    }

    @FXML
    private void editProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            nameField.setText(selectedProduct.getName());
            priceField.setText(String.valueOf(selectedProduct.getPrice()));
            productListView.getItems().remove(selectedProduct);
        } else {
            showAlert("Erreur", "Veuillez sélectionner un produit à modifier.");
        }
    }

    @FXML
    private void deleteProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productListView.getItems().remove(selectedProduct);
        } else {
            showAlert("Erreur", "Veuillez sélectionner un produit à supprimer.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
