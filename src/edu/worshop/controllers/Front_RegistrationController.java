/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import entities.User;
import services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class Front_RegistrationController implements Initializable {

    @FXML
    private TextField full_nameF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField passwordFF;
    @FXML
    private TextField addressF;
    @FXML
    private DatePicker dateNaissanceField;
    @FXML
    private ComboBox<String> roles;

    ServiceUser su = new ServiceUser();
    @FXML
    private Button conn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roles.getItems().addAll("ROLE_PATIENT", "ROLE_MEDECIN");
    }

    @FXML
    private void Register(MouseEvent event) {

        String email = emailF.getText();
        String password = passwordFF.getText();
        String address = addressF.getText();
        String full_name = full_nameF.getText();
        java.sql.Date dateNaissance = new java.sql.Date(Date.valueOf(dateNaissanceField.getValue()).getTime());
        String role = roles.getValue();
        System.out.println(role);
        // Check if email is valid
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email invalide", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            // Show an error message and return
            Alert alert = new Alert(AlertType.ERROR, "Mot de passe doit contenir 8 characteres", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        // Check if address is not empty
        // Check if any field is null
        if (email.isEmpty() || password.isEmpty() || address.isEmpty() || full_name.isEmpty() || dateNaissance == null) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs", ButtonType.OK);
            alert.showAndWait();

        }
        if (address.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Adresse ne peut etre vide", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if full name is not empty
        if (full_name.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Pseudo ne peut etre vide", ButtonType.OK);
            alert.showAndWait();
            return;

        }
        if (su.emailExist(email)) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email déja utilisé", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Mot de passe doit contenir 8 characteres", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // If all checks pass, create the user object and add it to the database
        User s = new User(email, address, password, full_name, role, dateNaissance);
        System.out.println(s);
        su.register(s);

        // Load and display the login interface
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/Front_Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }

    }

    @FXML
    private void RedirectToLogin(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/Front_Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }
    }

}
