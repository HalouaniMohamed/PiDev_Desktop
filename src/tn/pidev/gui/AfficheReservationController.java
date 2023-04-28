/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.pidev.entites.Reservation;
import tn.pidev.services.ReservationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
 
    
    @FXML
    private TableColumn<Reservation, String> tfID;

    @FXML
    private TableColumn<Reservation, String> tfName;

    @FXML
    private TableColumn<Reservation, String> tfNombre;
    
     
     
    @FXML
    private TableColumn<Reservation, String> tfEmail;
     @FXML
    private TableView<Reservation> ReservationView;
     
    
    ObservableList<Reservation> listeB = FXCollections.observableArrayList();
    public void show(){
       
    ReservationService bs=new ReservationService();
    listeB=bs.afficher();
    tfID.setCellValueFactory(new PropertyValueFactory<>("id"));
    tfName.setCellValueFactory(new PropertyValueFactory<>("enom"));
    tfNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place_areserver"));
      
    tfEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
  
    ReservationView.setItems(listeB);
    }
    
  


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         System.out.println("odk");

    show();
}
   @FXML private Button returnE;
    
     @FXML
    private void goE(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficheE.fxml"));
        Parent root = loader.load();
        returnE.getScene().setRoot(root);

    }

    
}
