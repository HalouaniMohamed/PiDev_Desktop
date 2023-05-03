package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import entities.Post;
import services.PostService;
import entities.Commentaire;
import services.CommentaireService;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class AdminPostController implements Initializable {
    
    @FXML
    private TextField tfId_User;
    @FXML
    private TextField tfNom_Utilisateur;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfPublication;
    @FXML
    private Button ajouterBtn;
    @FXML
private ListView<Post> listViewQuestion;
   

    private PostService postService = new PostService();

    /**
     * Initializes the controller class.
     */
    
     public void initialize(URL url, ResourceBundle rb) {
        // Afficher la liste des questions dans la ListView
        List<Post> questions = postService.afficher();
        ObservableList<Post> observableQuestions = FXCollections.observableArrayList(questions);
        listViewQuestion.setItems(observableQuestions);
        
    }
    
 

@FXML
void SupprimerPost(ActionEvent event) {
        Post selectedLN =  listViewQuestion.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer  ");
            alert.setContentText("Veuillez selectionner pour supprimer !");
            alert.showAndWait();
        } else {
            PostService ps = new PostService();
            System.out.println(selectedLN.getId());
            ps.supprimer(selectedLN);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Post supprime!");
            alert.showAndWait();

            // Actualiser le TableView
             List<Post> questions = postService.afficher();
        ObservableList<Post> observableQuestions = FXCollections.observableArrayList(questions);
        listViewQuestion.setItems(observableQuestions);
            
        }
    }

     



    
    
    

    
}
