/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tn.esprit.entities.Commentaire;
import tn.esprit.services.CommentaireService;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;



/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutController implements Initializable {
    

     @FXML
    private Label textlabel;
    @FXML
    private Label lb;
    @FXML
private ListView<Commentaire> listViewCommentaires;
    @Override
   
public void initialize(URL url, ResourceBundle rb) {
    CommentaireService ps = new CommentaireService();
    lb.setText(textlabel.getText());

    // Récupérer la liste des questions
    List<Commentaire> questions = ps.afficher();

    // Afficher la liste des questions dans la ListView
    listViewCommentaires.getItems().setAll(questions);
}
   
     public void setLabel(String text){
        this.textlabel.setText(text);
    }
     public void setListView(List<Commentaire> posts) {
        ObservableList<Commentaire> observableList = FXCollections.observableArrayList(posts);
        listViewCommentaires.setItems(observableList);
    }
     
    
}
