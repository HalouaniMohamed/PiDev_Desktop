/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tn.pidev.entites.Evenements;
import tn.pidev.entites.Reservation;
import tn.pidev.services.ReservationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ConnexionReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
	   @FXML
	    private TextField txmail;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private boolean isValid(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
    @FXML
    private void verifMail(ActionEvent reservation) {
    	Reservation selectedReservation =new Reservation();
         if (txmail.getText().isEmpty()   ) 
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("One or more fields are empty!");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        } 
      if (isValid(txmail.getText()) == false) {
 			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Email");
			alert.setHeaderText(null);
			alert.setContentText("entrer une adresse email valide");
			alert.show();
			txmail.requestFocus();
			txmail.selectAll();
			return;
		}       	

    
		    ObservableList<Reservation> listeB = FXCollections.observableArrayList();
             // Call the EventService to add the event to the database
            ReservationService reservationService = new ReservationService();
            listeB= (ObservableList<Reservation>) reservationService.afficher();
       	 System.out.println( listeB);
            String email = txmail.getText();
            boolean emailExists = false;
            for (Reservation r : listeB) {
                if (r.getEmail().equals(email)) {
                    emailExists = true;
                    break;
                   
                }
            }
            if (emailExists) {
                // Set the selected reservation to the first one with the specified email
                selectedReservation = null;
                for (Reservation r : listeB) {
                    if (r.getEmail().equals(email)) {
                        selectedReservation = r;
                        break;
                    }
                }
                
                if (selectedReservation != null) {
                    try {
                        // Load the AfficheRUserr.fxml interface
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheRUserr.fxml"));
                        Parent root = loader.load();
                        AfficheRUserrController controller = loader.getController();
                        
                        // Set the reservation data in the controller
                        controller.setReservation(selectedReservation.getEmail());

                        // Show the AfficheRUserr.fxml interface
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
             
            } else {
            	   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("Non Trouvee!");
                   ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                   alert.getButtonTypes().setAll(okButton);
                   alert.showAndWait();
            }            
        

        }
    }
    
 
