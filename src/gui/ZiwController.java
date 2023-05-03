package gui;

import static com.github.plushaze.traynotification.notification.Notifications.SUCCESS;
import com.github.plushaze.traynotification.notification.TrayNotification;

import entities.Post;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import services.PostService;
import tools.SessionManager;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ZiwController implements Initializable {

    @FXML
    private GridPane gridPane;
    private User currentUser;
    @FXML
    private HBox pagination;

    private PostService postService = new PostService();
    @FXML
    private Button voirP;
    @FXML
    private Button AjouterP;
    @FXML
    private TextField id_recherche;
    @FXML
    private Button btn_sort;
    @FXML
    private Button id_like;
    @FXML
    private Button id_dislike;

    @FXML
    private void goQ(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AffichePuser.fxml"));
        Parent root = loader.load();
        voirP.getScene().setRoot(root);

    }

    @FXML
    private void addP(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Post.fxml"));
        Parent root = loader.load();
        AjouterP.getScene().setRoot(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillGridPane();
        currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            AjouterP.setDisable(true);
            voirP.setDisable(true);
        }

    }

    private void fillGridPane() {
        List<Post> questions = postService.afficher();
        int row = 0;
        int column = 0;
        for (Post question : questions) {
            Label label = new Label(question.toString());
            gridPane.add(label, column, row);

            HBox hbox = new HBox(10); // create a HBox with 10 margin
            Button likeButton = new Button("Like");
            likeButton.setOnAction(event -> {
                postService.setlike(question);
                // afficher une notification
                String title = "Notification de poste";
                String message = "Vous avez liké le post avec succès!";
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotification(SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            });
            hbox.getChildren().add(likeButton);

            Button dislikeButton = new Button("Dislike");
            dislikeButton.setOnAction(event -> {
                postService.setdislike(question);
                // afficher une notification
                String title = "Notification de poste";
                String message = "Vous avez disliké le post avec succès!";
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotification(SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            });
            likeButton.setStyle("-fx-background-color: #66B2FF; -fx-text-fill: white;");
            dislikeButton.setStyle("-fx-background-color: #66B2FF; -fx-text-fill: white;");

            hbox.getChildren().add(dislikeButton);

            gridPane.add(hbox, column + 1, row); // add the HBox to the gridPane

            column += 3;

            if (column >= gridPane.getColumnConstraints().size()) {
                column = 0;
                row++;
            }
        }
    }

    private void trier(ActionEvent event) {
        List<Post> questions = postService.afficher();
        Collections.sort(questions, Comparator.comparing(Post::getPublication));

        gridPane.getChildren().clear();

        int row = 0;
        int column = 0;
        for (Post question : questions) {
            Label label = new Label(question.toString());
            gridPane.add(label, column, row);
            column++;

            if (column == 2) {
                column = 0;
                row++;
            }
        }
    }

    //Recherche
    @FXML
    private void cherchepost(ActionEvent event) {
        String searchTerm = id_recherche.getText().toLowerCase();

        List<Post> questions = postService.afficher();
        List<Post> filteredQuestions = new ArrayList<>();

        for (Post question : questions) {
            if (question.getDescription().toLowerCase().contains(searchTerm)
                    || question.getPublication().toLowerCase().contains(searchTerm)
                    || question.getNom_utilisateur().toLowerCase().contains(searchTerm)) {
                filteredQuestions.add(question);
            }
        }

        gridPane.getChildren().clear();

        int row = 0;
        int column = 0;
        for (Post question : filteredQuestions) {
            Label label = new Label(question.toString());
            gridPane.add(label, column, row);
            column++;

            if (column == 2) {
                column = 0;
                row++;
            }
        }
    }

}
