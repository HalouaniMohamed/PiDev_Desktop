/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import tn.eshkili.entities.Mood;
import tn.eshkili.services.Emailsender;
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
        
        
         String message = "Dear Client,\n"
                        + "\n"
                        + "I am writing this email to confirm your location reservation for the following details:\n"
                        + "\n"
                        + "user_id  : " + user_id  + "\n"
                        + "mood_id  : " + mood_id + "\n"
                        + "description  : " + description  + "\n"
                        + "mood  : " + mood + "\n"

                       
                        //+ "We are pleased to inform you that your reservation has been successfully processed, and we have reserved the required number of seats for you. Your confirmation number is [Enter confirmation number].\n"
                        + "\n";

                Emailsender.sendEmail_add("souha.sghaier@esprit.tn", message);
                 
        Notifications notificationBuilder = Notifications.create()
                .title("mood ajouté")
                .text("saved")
               // .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
                 notificationBuilder.darkStyle();
                 notificationBuilder.show();
                 
                
                 try {
        Parent page1 = FXMLLoader.load(getClass().getResource("../gui/AfficherMood.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjouterMoodController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
                }
}

        
    
    

