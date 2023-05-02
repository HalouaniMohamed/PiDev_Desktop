/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.rendez_vous;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import services.RendezVous;


/**
 * FXML Controller class
 *
 * @author Mongi
    */
   public class ModifierRVController implements Initializable {

       /**
        * Initializes the controller class.
        */
       @FXML
       private TextField nomField;

       @FXML
       private TextField prenomField;

       @FXML
       private TextField causeField;

       @FXML
       private TextField  descriptionField;

       @FXML
       private DatePicker dateField;

       @FXML
       private Button enregistrerButton;

       private rendez_vous rv;
       private LocalDate date_rv;


       @Override
       public void initialize(URL url, ResourceBundle rb) {
           // TODO
       }

       public void initData(rendez_vous rv) {

           this.rv = rv;
           nomField.setText(rv.getNom());
           prenomField.setText(rv.getPrenom());
           causeField.setText(rv.getCause());
                   descriptionField.setText(rv.getDescription());
                   
                    
;

       }

       @FXML
       private void handleEnregistrer(ActionEvent event) {
           rv.setNom(nomField.getText());
           rv.setPrenom(prenomField.getText());
           rv.setCause(causeField.getText());
           rv.setDescription(descriptionField.getText());
 



           RendezVous rendezvous = new RendezVous();
           rendezvous.modifier(rv.getId(), rv);

           Stage stage = (Stage) enregistrerButton.getScene().getWindow();
           stage.close();
       }

   }
