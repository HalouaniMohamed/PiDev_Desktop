/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tn.pidev.entites.Reservation;
import tn.pidev.services.ReservationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheRController implements Initializable {


 
    
 
    
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
    listeB=    bs.afficher();
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
      
    
}
