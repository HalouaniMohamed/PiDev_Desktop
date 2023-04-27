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
import tn.eshkili.entities.Mood;
import tn.eshkili.services.Mood1;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterMoodController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfUserID;
    @FXML
    private TextField tfmood;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfmoodID;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savemood(ActionEvent event) {
        int id= Integer.parseInt(tfID.getText());
        int user_id= Integer.parseInt(tfUserID.getText());
        String mood= tfmood.getText();
        String description= tfdesc.getText();
        int mood_id= Integer.parseInt(tfmoodID.getText());
        
        Mood m = new Mood(id, user_id, mood_id, description, mood);
        Mood1 M = new Mood1();
        M.ajouterMood(m);
        
        
    }
    
}
