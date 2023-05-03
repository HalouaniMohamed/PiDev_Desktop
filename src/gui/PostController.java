package gui;


import static com.github.plushaze.traynotification.notification.Notifications.SUCCESS;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.util.Duration;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import javafx.event.EventHandler;
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
    @FXML
      private Button btn_sort;
    

    private PostService postService = new PostService();
    
     @FXML
    private TextField id_recherche;
     
      @FXML
    private Button id_like;
       @FXML
    private Button id_dislike;
      
       
      

    
    

     /**
     * Initializes the controller class.
     */
    public PostController() {}

    @Override
public void initialize(URL url, ResourceBundle rb) {
    btn_sort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           
            listViewQuestion.getItems().sort(Comparator.comparing(Post::getDescription));
              listViewQuestion.getSelectionModel().clearSelection();
   
           }
        });
    //  listViewQuestion.refresh();
    
    // Afficher la liste des questions dans la ListView
    List<Post> questions = postService.afficher();
    ObservableList<Post> observableQuestions = FXCollections.observableArrayList(questions);
    listViewQuestion.setItems(observableQuestions);
    
   
    

}

    
     @FXML
private void AjouterPost(ActionEvent event) {
        
    // Vérifier si tous les champs sont remplis
    if (tfId_User.getText().isEmpty() || tfNom_Utilisateur.getText().isEmpty() || tfDescription.getText().isEmpty() || tfPublication.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Tous les champs doivent être remplis");
        alert.showAndWait();
        return;
    }

    // Vérifier si le nom d'utilisateur ne contient pas de caractères spéciaux
    String nomUtilisateur = tfNom_Utilisateur.getText();
    if (!nomUtilisateur.matches("[a-zA-Z0-9_]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Le nom d'utilisateur ne doit contenir que des lettres, des chiffres ou des tirets bas");
        alert.showAndWait();
        return;
    }
     int id;
    try {
        // Vérifier que l'ID est un entier
        id = Integer.parseInt(tfId_User.getText());
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ID invalide");
        alert.setHeaderText(null);
        alert.setContentText("L'ID doit être un entier.");
        alert.showAndWait();
        return;
    }

    // Ajouter le post
    Post p = new Post(Integer.parseInt(tfId_User.getText()), nomUtilisateur, tfDescription.getText(), tfPublication.getText());
    PostService ps = new PostService();
    ps.ajouter(p);

    // Afficher la fenêtre des détails
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

//Recherche 
 @FXML
    private void cherchepost(ActionEvent event) {
        
       
        ObservableList<Post> list = FXCollections.observableArrayList(postService.afficher());
        FilteredList<Post> filteredData = new FilteredList<>(list, b -> true);
        id_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Post -> {
                if (newValue == null || newValue.isEmpty()) {
                  
                    System.out.println("bien");
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Post.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Post.getPublication().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Post.getNom_utilisateur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                
                }
                
                 else {
                    return false;
                }
            });
        });

       SortedList<Post> sortedData = new SortedList<>(filteredData);

        //sortedData.comparatorProperty().bind(listViewQuestion.comparatorProperty());

        listViewQuestion.setItems(sortedData);
    }
    
    //like post
    
     @FXML
private void likepost(ActionEvent event) {
    
       Post selectedLN =  listViewQuestion.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter un like  ");
            alert.setContentText("Veuillez selectionner pour ajouter un like !");
            alert.showAndWait();
        }
            else {
            PostService ps = new PostService();
            System.out.println(selectedLN.getId());
            ps.setlike(selectedLN);
            

          // Notification LIKE 
           String title="Notification de poste";
           String message="Vous avez liké le post avec succès!";
           TrayNotification tray =new TrayNotification();
           tray.setTitle(title);
           tray.setMessage(message);
           tray.setNotification(SUCCESS);
           tray.showAndDismiss(Duration.millis(3000));

      
    }
}


//dislike post
     @FXML
private void dislikepost(ActionEvent event) {
       Post selectedLN =  listViewQuestion.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter un dislike  ");
            alert.setContentText("Veuillez selectionner pour ajouter un dislike !");
            alert.showAndWait();
        }
            else {
            PostService ps = new PostService();
            System.out.println(selectedLN.getId());
            ps.setdislike(selectedLN);

            // Notification DISLIKE 
           String title="Notification de poste";
           String message="Vous avez disliké le post avec succès";
           TrayNotification tray =new TrayNotification();
           tray.setTitle(title);
           tray.setMessage(message);
           tray.setNotification(SUCCESS);
           tray.showAndDismiss(Duration.millis(3000));

      
    }
    
}

 
            



 

}
                    
    
    

            
    


     

    
    
    

    


