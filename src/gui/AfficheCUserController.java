/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import services.CommentaireService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficheCUserController implements Initializable {
       @FXML 
    private Button retour;
    @FXML
    private void  back(javafx.event.ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ZiwCommentaire.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);


    }
         @FXML private ListView<Commentaire> listViewCommentaire;
	  private int id_user;
   private List<Commentaire> commentaires;

	 public void setReservation(int idU) {
	     this.id_user= idU;
	     System.out.println(id_user);
	     CommentaireService es = new CommentaireService();
	     commentaires = es.afficherCommentaireParId(id_user);
	     if (commentaires != null) {
	         listViewCommentaire.getItems().addAll(commentaires);
	     }}
             
      // private CommentaireService commentaireService = new CommentaireService();
	 
        
          
	
   

    private CommentaireService  CommentaireService = new CommentaireService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
void ModifierCommentaire (ActionEvent event) {
    
        Commentaire selectedLN =  listViewCommentaire.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
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
