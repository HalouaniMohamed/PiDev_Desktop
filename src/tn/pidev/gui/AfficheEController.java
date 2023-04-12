/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.gui;

import java.awt.Dialog;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

 import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.pidev.entites.Evenements;
import tn.pidev.services.EvenementsService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheEController implements Initializable {

    @FXML
    private AnchorPane id;
    @FXML
    private TableView<Evenements> categoriesView;
    private TableColumn<Evenements, String> tfNom;
    private TableColumn<Evenements, String>tfLieu;
    private TableColumn<Evenements, String> tfDate;
    private TableColumn<Evenements, String> tfDescription;
    private TableColumn<Evenements, String> tfNbr;
    private TableColumn<Evenements, String> tfType;
    ObservableList<Evenements> listeB = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> tfID;

    @FXML
    private TableColumn<?, ?> tfName;

    @FXML
    private TableColumn<?, ?> tfNombre;
    
     
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<?, ?> tfEmail;
    public void show(){
    	EvenementsService bs=new EvenementsService();
    listeB=bs.afficher();
    tfNom.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
    tfLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_evenement"));
    tfDate.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));
     
       

    tfDescription.setCellValueFactory(new PropertyValueFactory<>("description_evenement"));
    tfNbr.setCellValueFactory(new PropertyValueFactory<>("nbr_de_places"));
    tfType.setCellValueFactory(new PropertyValueFactory<>("type"));
    
 
    categoriesView.setItems(listeB);
    }
    
    void Supprimer(MouseEvent event) {
        Evenements selectedLN =  categoriesView.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer  ");
            alert.setContentText("Veuillez selectionner pour supprimer !");
            alert.showAndWait();
        } else {
            EvenementsService bs = new EvenementsService();
            System.out.println(selectedLN.getId());
            bs.supprimer(selectedLN.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("Evenements supprime!");
            alert.showAndWait();

            // Actualiser le TableView
            show();
        }
    }
    void Modifier(MouseEvent event) {
        Evenements selectedLN =  categoriesView.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier  ");
            alert.setContentText("Veuillez selectionner pour modifier !");
            alert.showAndWait();
        }else {
            // Show an input dialog to get the new event details.
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier un evenement");
            dialog.setHeaderText("Modifier les champs de l'evenement");
            dialog.setContentText("Nom de l'evenement:");

            // Set the default value of the input field to the current event name.
            dialog.getEditor().setText(selectedLN.getNom_evenement());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                // Update the event name with the new value.
                selectedLN.setNom_evenement(result.get());
                // TODO: Update the other event details using similar steps.

                EvenementsService bs = new EvenementsService();
                
                bs.modifier(selectedLN);
                // Show a confirmation alert.
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("L'evenement a ete modifie avec succe");
                alert.setContentText("Les modifications ont ete enregistrees.");
                alert.showAndWait();
            }
            
        }
        categoriesView.refresh();
 
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    show();
}
    
     
 
    
}
