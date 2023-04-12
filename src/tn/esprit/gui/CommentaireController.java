package tn.esprit.gui;

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
import tn.esprit.entities.Commentaire;
import tn.esprit.services.CommentaireService;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class CommentaireController implements Initializable {
    
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
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // Afficher la liste des commentaires dans la ListView
        
        List<Commentaire> commentaires =  CommentaireService.afficher();
        ObservableList<Commentaire> observableCommentaires = FXCollections.observableArrayList(commentaires);
        listViewCommentaire.setItems(observableCommentaires);
    }
    
    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        

       Commentaire c = new Commentaire(Integer.parseInt(tfId_User.getText()), Integer.parseInt(tfcommentaires_id.getText()), tfreponse.getText());

        CommentaireService ps = new  CommentaireService();
       
        ps.ajouter(c);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout.fxml"));

        try {
            Parent root = loader.load();

            DetailsController dc = loader.getController();
           dc.setLabel("Commentaire:" + " " + c.getReponse() );

            tfreponse.getScene().setRoot(root);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    @FXML
void ModifierCommentaire (ActionEvent event) {
        Commentaire selectedLN =  listViewCommentaire.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier  ");
            alert.setContentText("Veuillez selectionner pour modifier !");
            alert.showAndWait();
        }else {
            // Show an input dialog to get the new event details.
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier un commentaire");
            dialog.setHeaderText("Modifier les champs du commentaire");
            dialog.setContentText("Reponse:");

            // Set the default value of the input field to the current event name.
            dialog.getEditor().setText(selectedLN.getReponse());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                // Update the event name with the new value.
                selectedLN.setReponse(result.get());
                // TODO: Update the other event details using similar steps.

                CommentaireService bs = new CommentaireService();
                
                bs.modifier(selectedLN);
                // Show a confirmation alert.
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Le commentairet a ete modifie avec succe");
                alert.setContentText("Les modifications ont ete enregistrees.");
                alert.showAndWait();
            }
            
        }
        listViewCommentaire.refresh();
 
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
             List<Commentaire> questions = CommentaireService.afficher();
        ObservableList<Commentaire> observableQuestions = FXCollections.observableArrayList(questions);
        listViewCommentaire.setItems(observableQuestions);
            
        }
    }

     



    
    
    

    
}
