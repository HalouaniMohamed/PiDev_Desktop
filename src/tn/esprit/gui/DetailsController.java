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
import tn.esprit.entities.Post;
import tn.esprit.services.PostService;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;



/**
 * FXML Controller class
 *
 * @author asus
 */
public class DetailsController implements Initializable {
    

     @FXML
    private Label textlabel;
    @FXML
    private Label lb;
    @FXML
private ListView<Post> listViewQuestions;
    @Override
   
public void initialize(URL url, ResourceBundle rb) {
    PostService ps = new PostService();
    lb.setText(textlabel.getText());

    // Récupérer la liste des questions
    List<Post> questions = ps.afficher();

    // Afficher la liste des questions dans la ListView
    listViewQuestions.getItems().setAll(questions);
}
   
     public void setLabel(String text){
        this.textlabel.setText(text);
    }
     public void setListView(List<Post> posts) {
        ObservableList<Post> observableList = FXCollections.observableArrayList(posts);
        listViewQuestions.setItems(observableList);
    }
     
    
}
