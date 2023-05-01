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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
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
    private TextField passwordF;
     @FXML
    private TextField passwordFF;
    @FXML
    private TextField addressF;
    @FXML
    private DatePicker dateNaissanceField;
    
    
    ServiceUser su = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Register(MouseEvent event) {
        
        String email = emailF.getText();
        String password = passwordFF.getText();
        String address = addressF.getText();
        String full_name = full_nameF.getText();
        java.sql.Date dateNaissance = new java.sql.Date(Date.valueOf(dateNaissanceField.getValue()).getTime());
        

        // Check if email is valid
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid email address", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if password is at least 8 characters long
        if (password.length() < 8) {
        // Show an error message and return
        Alert alert = new Alert(AlertType.ERROR, "Password must be at least 8 characters long", ButtonType.OK);
        alert.showAndWait();
        return;
    }
        // Check if address is not empty
        // Check if any field is null
    if (email.isEmpty() || password.isEmpty() || address.isEmpty() || full_name.isEmpty() || dateNaissance == null) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
        alert.showAndWait();
        
    }
        if (address.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Address cannot be empty", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if full name is not empty
        if (full_name.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Full name cannot be empty", ButtonType.OK);
            alert.showAndWait();
            return;

        }
        if (su.emailExist(email)) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Email already exists", ButtonType.OK);
        alert.showAndWait();
        return;
    }
        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters long.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // If all checks pass, create the user object and add it to the database
        User s = new User(email, address, password, full_name, dateNaissance);
        su.register(s);
        
        
         // Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "thank you for joining Sportify!", ButtonType.OK);
        alert.showAndWait();
        
        // Load and display the login interface
    try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Front_Login.fxml"));
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
