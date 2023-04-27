/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.eshkili.entities.Mood;
import tn.eshkili.services.Mood1;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherMoodController implements Initializable {

    @FXML
    private TableView<Mood> Moodview;
    @FXML
    private TableColumn<Mood, Integer> tfid;
    @FXML
    private TableColumn<Mood, Integer> tfuser;
    @FXML
    private TableColumn<Mood, Integer> tfmoodid;
    @FXML
    private TableColumn<Mood, String> tfmood;
    @FXML
    private TableColumn<Mood, String> tfdesc;
     ObservableList<Mood> listeB = FXCollections.observableArrayList();
    @FXML
    private Button buttmod;
    @FXML
    private Button buttsupp;

    
        public void show(){
    	Mood1 bs=new Mood1();
        List<Mood> listeMoods = bs.afficherMood();
   
 
    tfid.setCellValueFactory(new PropertyValueFactory<>("id"));
    tfuser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    tfmoodid.setCellValueFactory(new PropertyValueFactory<>("mood_id"));
     
       

    tfmood.setCellValueFactory(new PropertyValueFactory<>("mood"));
    tfdesc.setCellValueFactory(new PropertyValueFactory<>("description"));

    
 
    Moodview.setItems(listeB);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }    

    @FXML
    private void modifierMood(javafx.event.ActionEvent event) {
        
        

    
//     private void showAlert(String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    
//    }
        
         Mood selectedLN =  (Mood) Moodview.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier  ");
            alert.setContentText("Veuillez selectionner pour modifier !");
            alert.showAndWait();
        }else {
            // Show an input dialog to get the new event details.
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier un mood");
            dialog.setHeaderText("Modifier les champs du mood");
            dialog.setContentText("Nom de mood:");

            // Set the default value of the input field to the current event name.
            dialog.getEditor().setText(selectedLN.getMood());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                // Update the event name with the new value.
                selectedLN.setMood(result.get());
                // TODO: Update the other event details using similar steps.

                Mood1 bs = new Mood1();
                
                bs.modifierMood(selectedLN);
                // Show a confirmation alert.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Mood a ete modifie avec succe");
                alert.setContentText("Les modifications ont ete enregistrees.");
                alert.showAndWait();
            }
            
        }
        Moodview.refresh();
 
    }

    @FXML
    private void supprimermood(ActionEvent event) {
        
                
        Mood selectedLN =  Moodview.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer  ");
            alert.setContentText("Veuillez selectionner pour supprimer !");
            alert.showAndWait();
        } else {
            Mood1 bs = new Mood1();
            System.out.println(selectedLN.getId());
            bs.supprimerMood(selectedLN.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("Mood supprime!");
            alert.showAndWait();

            // Actualiser le TableView
            show();
        }
    }
    
    
}
