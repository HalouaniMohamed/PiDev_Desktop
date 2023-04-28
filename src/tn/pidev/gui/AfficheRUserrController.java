/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import tn.pidev.entites.Evenements;
import tn.pidev.entites.Reservation;
import tn.pidev.services.EvenementsService;
import tn.pidev.services.ReservationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */public class AfficheRUserrController implements Initializable {
	 
 	 @FXML private ListView<Reservation> listView;
	 private String mail;
	 private List<Reservation> reservations;

	 public void setReservation(String emmail) {
	     this.mail = emmail;
	     System.out.println(mail);
	     ReservationService es = new ReservationService();
	     reservations = es.afficherParEmail(mail);
	     if (reservations != null) {
	         listView.getItems().addAll(reservations);
	     }
	 }

 	    public void initialize(URL location, ResourceBundle resources) {
	      

 	       listView.setCellFactory((Callback<ListView<Reservation>, ListCell<Reservation>>) new Callback<ListView<Reservation>, ListCell<Reservation>>() {
 	    	    public ListCell<Reservation> call(ListView<Reservation> param) {
 	    	        return new ListCell<Reservation>() {

 	    	            @Override
 	    	            protected void updateItem(Reservation item, boolean empty) {
 	    	                super.updateItem(item, empty);

 	    	                if (empty || item == null) {
 	    	                    setText(null);
 	    	                    setGraphic(null);
 	    	                } else {
 	    	                    
 	    	                    HBox hbox = new HBox();
 	    	                    hbox.setAlignment(Pos.CENTER_LEFT);
 	    	                    hbox.setSpacing(10);

 	    	 
 	    	                    Label nomLabel = new Label(item.getE().toString());

 	    	                   nomLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

 	    	                    Label emaill = new Label(item.getEmail());
 	    	                   emaill.setStyle("-fx-font-size: 14;");
                                    
 	    	                   Label nb = new Label(String.valueOf(item.getNombre_de_place_areserver()));
 	    	                  nb.setStyle("-fx-font-size: 14px;");
 

 	    	                    
 	    	                    hbox.getChildren().addAll(nomLabel, emaill,nb );

 	    	                    // Afficher la HBox comme contenu de la cellule
 	    	                    setGraphic(hbox);
 	    	                }
 	    	            }
 	    	        };
 	    	    }
 	    	});

 	    	
 
 	    }


 	   @FXML
 	  private void supprimer(ActionEvent event) throws SQLException {
 	      // R�cup�rer la liste de r�servations
 	      ObservableList<Reservation> reservations = listView.getItems();
Reservation res = listView.getSelectionModel().getSelectedItem(); 
 	      // V�rifier s'il y a des r�servations dans la liste
 	      if (reservations.size() == 0) {
 	          Alert alert = new Alert(AlertType.INFORMATION);
 	          alert.setTitle("Information");
 	          alert.setHeaderText(null);
 	          alert.setContentText("Il n'y a aucune réservation à supprimer.");
 	          alert.showAndWait();
 	          return;
 	      }

 	      // Afficher une alerte de confirmation
 	      Alert alert = new Alert(AlertType.CONFIRMATION);
 	      alert.setTitle("Confirmation de suppression");
 	      alert.setHeaderText(null);
 	      alert.setContentText("etes-vous sur de vouloir supprimer la réservation ?");
 	      Optional<ButtonType> result = alert.showAndWait();
 	      if (result.get() != ButtonType.OK) {
 	          return;
 	      }

 	      // Supprimer toutes les r�servations
 	 
 		     ReservationService es = new ReservationService();
                       
int nbr_place = res.getNombre_de_place_areserver();
Evenements e = es.getEvent(res.getId());

               System.out.println(e.getId());
 	          es.supprimer(res.getId());
                  e.setNbr_de_places(e.getNbr_de_places()+nbr_place);
                  new EvenementsService().modifier(e);
 	     // e.setNbr_de_places(e.getNbr_de_places()+nbr_place);
            
            //  ss.modifier(e);
reservations.clear();
 	      // Rafra�chir la liste de r�servations
               setReservation(res.getEmail());

 	    	
 	  }
 	  @FXML
 	 private void modifier(ActionEvent event) {
 	     // R�cup�rer l'�l�ment s�lectionn� dans le ListView
 	     Reservation selectedReservation = listView.getSelectionModel().getSelectedItem();

 	     // V�rifier que l'utilisateur a bien s�lectionn� un �l�ment
 	     if (selectedReservation == null) {
 	         Alert alert = new Alert(AlertType.WARNING);
 	         alert.setTitle("Aucune reservation sélectionné");
 	         alert.setHeaderText(null);
 	         alert.setContentText("Veuillez sélectionner une réservation à modifier.");
 	         alert.showAndWait();
 	         return;
 	     }

 	     // Afficher une bo�te de dialogue pour permettre � l'utilisateur de modifier la r�servation
 	     TextInputDialog dialog = new TextInputDialog(String.valueOf(selectedReservation.getNombre_de_place_areserver()));
 	     dialog.setTitle("Modifier la r�servation");
 	     dialog.setHeaderText(null);
 	     dialog.setContentText("Veuillez saisir le nouveau nombre de places à réserver:");

 	     Optional<String> result = dialog.showAndWait();
 	     if (result.isPresent()) {
 	         // V�rifier que la saisie est valide (nombre entier positif)
 	         String newValue = result.get();
 	         try {
 	             int newNombreDePlaces = Integer.parseInt(newValue);
 	             if (newNombreDePlaces < 1) {
 	                 throw new NumberFormatException();
 	             }
 ReservationService es = new ReservationService();
                       
int old_nbr_place = selectedReservation.getNombre_de_place_areserver();
int idd = selectedReservation.getId();
Evenements e = es.getEvent(selectedReservation.getId());
e.setNbr_de_places(e.getNbr_de_places()+old_nbr_place);
 	             // Modifier la r�servation dans la base de donn�es
 	             ReservationService service = new ReservationService();
 	             selectedReservation.setNombre_de_place_areserver(newNombreDePlaces);
 	             service.modifier(selectedReservation);
                      Reservation rr = es.getOneById(idd);
                      int new_nbr_place = rr.getNombre_de_place_areserver();
                      e.setNbr_de_places(e.getNbr_de_places()-new_nbr_place);
                      new EvenementsService().modifier(e);

 	             // Afficher une bo�te de dialogue de confirmation
 	             Alert alert = new Alert(AlertType.INFORMATION);
 	             alert.setTitle("Réservation modifiée");
 	             alert.setHeaderText(null);
 	             alert.setContentText("La reservation a été modifiée avec succés.");
 	             alert.showAndWait();

 	             // Rafra�chir la liste des r�servations dans le ListView
 	             ObservableList<Reservation> reservations = service.afficherParEmail(selectedReservation.getEmail());
 	             listView.setItems(reservations);

 	         } catch (NumberFormatException ex) {
 	             // Afficher une bo�te de dialogue d'erreur si la saisie est invalide
 	             Alert alert = new Alert(AlertType.ERROR);
 	             alert.setTitle("Saisie invalide");
 	             alert.setHeaderText(null);
 	             alert.setContentText("Veuillez saisir un nombre entier positif.");
 	             alert.showAndWait();
 	         }
 	     }
 	 }
 }
 






 
