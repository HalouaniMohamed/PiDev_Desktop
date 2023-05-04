package gui;

import static com.github.plushaze.traynotification.notification.Notifications.SUCCESS;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import entities.Post;
import entities.User;
import services.PostService;
import javafx.util.Duration;
import tools.SessionManager;

public class PostController implements Initializable {

    @FXML
    private Button retour;

    @FXML
    private void back(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ziw.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);

    }

    @FXML
    private TextField tfId_User;
    @FXML
    private TextField tfNom_Utilisateur;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfPublication;
    private ListView<Post> listViewQuestion;
    private Button btn_sort;

    private PostService postService = new PostService();

    private TextField id_recherche;

    @FXML
    private Button Btn;
    private User currentUser;

    /**
     * Initializes the controller class.
     */
    public PostController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //  listViewQuestion.refresh();
        // Afficher la liste des questions dans la ListView
        currentUser = SessionManager.getCurrentUser();

    }

    @FXML
    private void AjouterPost(ActionEvent event) {

        // Vérifier si tous les champs sont remplis
        if (tfDescription.getText().isEmpty() || tfPublication.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis");
            alert.showAndWait();
            return;
        }

        // Vérifier si le nom d'utilisateur ne contient pas de caractères spéciaux
        // Ajouter le post
        Post p = new Post(currentUser.getId(), currentUser.getFull_name(), tfDescription.getText(), tfPublication.getText());
        PostService ps = new PostService();
        ps.ajouter(p);

        // Afficher la fenêtre des détails
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
        try {
            Parent root = loader.load();
            DetailsController dc = loader.getController();
            dc.setLabel("Question:" + " " + p.getPublication() + "\n publiée par" + " " + p.getNom_utilisateur());
            tfDescription.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //like post
    private void likepost(ActionEvent event) {

        Post selectedLN = listViewQuestion.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter un like  ");
            alert.setContentText("Veuillez selectionner pour ajouter un like !");
            alert.showAndWait();
        } else {
            PostService ps = new PostService();
            System.out.println(selectedLN.getId());
            ps.setlike(selectedLN);

            // Notification LIKE
            String title = "Notification de poste";
            String message = "Vous avez liké le post avec succès!";
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotification(SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        }
    }

//dislike post
    private void dislikepost(ActionEvent event) {
        Post selectedLN = listViewQuestion.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter un dislike  ");
            alert.setContentText("Veuillez selectionner pour ajouter un dislike !");
            alert.showAndWait();
        } else {
            PostService ps = new PostService();
            System.out.println(selectedLN.getId());
            ps.setdislike(selectedLN);

            // Notification DISLIKE
            String title = "Notification de poste";
            String message = "Vous avez disliké le post avec succès";
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotification(SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        }

    }

}
