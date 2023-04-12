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
import tn.esprit.entities.Post;
import tn.esprit.services.PostService;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class PostController implements Initializable {
    
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
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // Afficher la liste des questions dans la ListView
        List<Post> questions = postService.afficher();
        ObservableList<Post> observableQuestions = FXCollections.observableArrayList(questions);
        listViewQuestion.setItems(observableQuestions);
    }
    
    @FXML
    private void AjouterPost(ActionEvent event) {
        

        Post p = new Post(Integer.parseInt( tfId_User.getText()), tfNom_Utilisateur.getText(), tfDescription.getText(), tfPublication.getText());
        PostService ps = new PostService();
       
        ps.ajouter(p);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));

        try {
            Parent root = loader.load();

            DetailsController dc = loader.getController();
           dc.setLabel("Question:" + " " + p.getPublication() + "\n publiée par"  + " " + p.getNom_utilisateur() );

            tfNom_Utilisateur.getScene().setRoot(root);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    @FXML
void ModifierPost(ActionEvent event) {
    Post selectedLN = listViewQuestion.getSelectionModel().getSelectedItem();
    if (selectedLN == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de modifier  ");
        alert.setContentText("Veuillez selectionner pour modifier !");
        alert.showAndWait();
    } else {
        // Show an input dialog to get the new event details.
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier un post");
        dialog.setHeaderText("Modifier les champs du post");

        // Set the default values of the input fields to the current post description and publication.
        dialog.getEditor().setText(selectedLN.getDescription());
        dialog.getEditor().setPromptText("Description");
        dialog.getEditor().setText(selectedLN.getPublication());
        dialog.getEditor().setPromptText("Publication");

        // Add a second input field for the post description.
        TextField descriptionField = new TextField();
        descriptionField.setText(selectedLN.getDescription());
        descriptionField.setPromptText("Description");

        // Set the content of the dialog to include both input fields.
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("Description:"), descriptionField, new Label("Publication:"), dialog.getEditor());
        dialog.getDialogPane().setContent(vbox);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // Update the post details with the new values.
            selectedLN.setDescription(descriptionField.getText());
            selectedLN.setPublication(result.get());

            PostService ps = new PostService();
            ps.modifier(selectedLN);

            // Show a confirmation alert.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Le post a ete modifie avec succes");
            alert.setContentText("Les modifications ont ete enregistrees.");
            alert.showAndWait();
        }
    }
    listViewQuestion.refresh();
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
