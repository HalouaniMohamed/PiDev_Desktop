/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.eshkili.entities.JournalMood;
import tn.eshkili.services.Journal;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterJournalController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfiduser;
    @FXML
    private TextField tfmood;
    @FXML
    private Button btnvalider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addJournal(ActionEvent event) {
        int id= Integer.parseInt(tfid.getText());
        int id_user= Integer.parseInt(tfiduser.getText());
        int moods_id= Integer.parseInt(tfmood.getText());
        
        JournalMood j = new JournalMood(id, id_user, moods_id);
        Journal J = new Journal();
        J.ajouterJournal(j);
        
    } 
    
}
