/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import services.PasswordEncryption;
import services.ServiceUser;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class Reset_PasswordController implements Initializable {

    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfConfirmer;
    @FXML
    private Button BtnReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnReset(ActionEvent event) throws Exception {
        Alert A = new Alert(Alert.AlertType.INFORMATION);
        if (!tfPassword.getText().equals("") && tfPassword.getText().equals(tfConfirmer.getText())) {
            ServiceUser su = new ServiceUser();
            //String encrypt = (tfPassword.getText());
            String encrypt = PasswordEncryption.encrypt(tfPassword.getText());

            su.ResetPaswword(Forget_PasswordController.EmailReset, encrypt);
            A.setContentText("Mot de passe modifié avec succes ! ");
            A.show();
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
        } else {
            A.setContentText("veuillez saisir un mot de passe conforme !");
            A.show();
        }
    }

    @FXML
    private void btnAnnulerReset(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Forget_Password.fxml"));
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
