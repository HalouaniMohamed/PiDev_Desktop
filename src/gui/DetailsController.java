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
import entities.Post;
import java.io.IOException;
import services.PostService;
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
public class DetailsController implements Initializable {
     @FXML 
    private Button retour;
    @FXML
    private void  back(javafx.event.ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Post.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);


    }
    

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
