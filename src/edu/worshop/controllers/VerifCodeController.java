/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

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
public class VerifCodeController implements Initializable {

    @FXML
    private TextField tfCode;
    @FXML
    private Button BtnConfirmerCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void BtnConfirmerCode(ActionEvent event) {
        if (Integer.parseInt(tfCode.getText()) == Forget_PasswordController.code) {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/Reset_Password.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
                //showAlert("Error loading");
            }
        } else {
            Alert A = new Alert(Alert.AlertType.WARNING);
            A.setContentText("Code erroné ! ");
            A.show();

        }
    }

    @FXML
    private void btnAnnulerCode(ActionEvent event) {
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
