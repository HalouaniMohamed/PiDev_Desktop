/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import tn.pidev.entites.Reservation;
import tn.pidev.services.ReservationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddRController implements Initializable {

    @FXML
    private TextField tfNbr;
    @FXML
    private ChoiceBox<?> cbCategorie;
    @FXML
    private TextField tfMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void addReservation(ActionEvent reservation) {

        // Check that all text fields have values
        if (tfNbr.getText().isEmpty() || tfMail.getText().isEmpty() ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("One or more fields are empty!");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        } else {
           

            Reservation reservationObj = new Reservation(Integer.parseInt(tfNbr.getText()), tfMail.getText());

            // Call the EventService to add the event to the database
            ReservationService reservationService = new ReservationService();
            reservationService.ajouter(reservationObj);
            
             
            // Show success message and clear text fields
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Reservation added successfully");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
            tfNbr.setText("");
            tfMail.setText("");
            

        }
    }
}
