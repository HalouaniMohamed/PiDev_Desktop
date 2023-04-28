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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.eshkili.entities.JournalMood;
import tn.eshkili.entities.Mood;
import tn.eshkili.services.Journal;
import tn.eshkili.services.Mood1;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherJournalController implements Initializable {

    @FXML
    private ListView<JournalMood> journalview;
    static int id,id_user,moods_id;   
    static JournalMood J = new JournalMood();
    private TableColumn<JournalMood, Integer> tfid;
    private TableColumn<JournalMood, Integer> tfiduser;
    private TableColumn<JournalMood, Integer> tfmoodsid;
    ObservableList<JournalMood> listeB = FXCollections.observableArrayList();
    @FXML
    private Button buttsupp;

    /**
     * Initializes the controller class.
     */
//    /*
//     public void show(){
//    	Journal bs=new Journal();
//        List<JournalMood> listeJournals = bs.afficherJournal();
//
//
//    tfid.setCellValueFactory(new PropertyValueFactory<>("id"));
//    tfiduser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
//    tfmoodsid.setCellValueFactory(new PropertyValueFactory<>("moods_id"));
//
//
//
//
//
//
//    journalview.setItems(listeB);
//    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //show();
         
              ListView<JournalMood> list1 = journalview;
    Journal inter = new Journal();
    List<JournalMood> list2 = inter.afficherJournal();
for (int i = 0; i < list2.size(); i++) {
    JournalMood E = list2.get(i);
    list1.getItems().add(E); 
    }    

    } 
    @FXML
    private void supprimerjournal(ActionEvent event) {
                
        JournalMood selectedLN =  journalview.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer  ");
            alert.setContentText("Veuillez selectionner pour supprimer !");
            alert.showAndWait();
        } else {
            Journal bs = new Journal();
            System.out.println(selectedLN.getId());
            bs.supprimerJournal(selectedLN.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("Journal supprime!");
            alert.showAndWait();

            // Actualiser le TableView
           // show();
        }
    }
    
}
