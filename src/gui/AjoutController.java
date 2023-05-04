/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import entities.Commentaire;
import java.io.IOException;
import services.CommentaireService;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;



/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutController implements Initializable {
      @FXML 
    private Button retour;
    @FXML
    private void  back(javafx.event.ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Commentaire.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);


    }
    

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

    // Récupérer la liste des commentaires
    List<Commentaire> commentaires = ps.afficher();

    // Afficher la liste des commentaires dans la ListView
    listViewCommentaires.getItems().setAll(commentaires);
}
   
     public void setLabel(String text){
        this.textlabel.setText(text);
    }
     public void setListView(List<Commentaire> commentaires) {
        ObservableList<Commentaire> observableList = FXCollections.observableArrayList(commentaires);
        listViewCommentaires.setItems(observableList);
    }
     
    
}
