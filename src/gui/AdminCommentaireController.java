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

import entities.Commentaire;
import services.CommentaireService;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class AdminCommentaireController implements Initializable {
    
    @FXML
    private TextField tfId_User;
    @FXML
    private TextField tfcommentaires_id;
    @FXML
    private TextField tfreponse;
    @FXML
    private Button ajouterbtn;
    @FXML
    
    private ListView<Commentaire> listViewCommentaire;
   

    private CommentaireService  CommentaireService = new CommentaireService();

    /**
     * Initializes the controller class.
     */
    
     public void initialize(URL url, ResourceBundle rb) {
      
      // Afficher la liste des commentaires dans la ListView
        
        List<Commentaire> commentaires =  CommentaireService.afficher();
        ObservableList<Commentaire> observableCommentaires = FXCollections.observableArrayList(commentaires);
        listViewCommentaire.setItems(observableCommentaires);
    }
     
@FXML
void SupprimerCommentaire(ActionEvent event) {
         Commentaire selectedLN =  listViewCommentaire.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer  ");
            alert.setContentText("Veuillez selectionner pour supprimer !");
            alert.showAndWait();
        } else {
            CommentaireService ps = new  CommentaireService();
            System.out.println(selectedLN.getId());
            ps.supprimer(selectedLN);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(" Commentaire supprime!");
            alert.showAndWait();

            // Actualiser le TableView
             List<Commentaire> commentaires = CommentaireService.afficher();
        ObservableList<Commentaire> observableCommentaires = FXCollections.observableArrayList(commentaires);
        listViewCommentaire.setItems(observableCommentaires);
            
        }
    }
        
    }
    
 


     



    
    
    

    

