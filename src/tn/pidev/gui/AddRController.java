/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.pidev.entites.Evenements;
import tn.pidev.entites.Reservation;
import tn.pidev.services.EvenementsService;
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
    private ChoiceBox<Evenements> cbCategorie;
    @FXML
    private TextField tfMail;
    private Stage stage;
	private EvenementsService evenementsService;
	private ObservableList<String>EventList;
	private boolean isValid(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
		 evenementsService = new EvenementsService();
		EventList = FXCollections.observableArrayList();
		setComboBoxItems();
 
		};
		private void setComboBoxItems() {
			// Retrieve all reclamations and their corresponding users
			ObservableList<Evenements> events = evenementsService.afficher();
 
			cbCategorie.setItems(events);
			
                       cbCategorie.setConverter(new StringConverter<Evenements>() {

	            @Override
	            public String toString(Evenements object) {
	                if (object != null) {
	                    return object.getNom_evenement();
	                } else return "";
	            }

                    @Override
	            public Evenements fromString(String string) {
	                return cbCategorie.getSelectionModel().getSelectedItem();
	            }
	        });
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
        } 
        else if (isValid(tfMail.getText()) == false) {
 			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Email");
			alert.setHeaderText(null);
			alert.setContentText("entrer une adresse email valide");
			alert.show();
			tfMail.requestFocus();
			tfMail.selectAll();
			return;
		}else {
           

            Reservation reservationObj = new Reservation(Integer.parseInt(tfNbr.getText()), tfMail.getText());
            reservationObj.setE(cbCategorie.getSelectionModel().getSelectedItem());

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
