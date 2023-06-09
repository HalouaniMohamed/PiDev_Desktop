/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.JournalMood;
import services.Journal;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterJournalController implements Initializable {

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

        int id_user = Integer.parseInt(tfiduser.getText());
        int moods_id = Integer.parseInt(tfmood.getText());

        JournalMood j = new JournalMood(id_user, moods_id);
        Journal J = new Journal();
        J.ajouterJournal(j);

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("AfficherJournal.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterMoodController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }

    }

    @FXML
    private void redirectToHome(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCart.fxml"));
        try {
            Parent root = loader.load();
            tfmood.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
