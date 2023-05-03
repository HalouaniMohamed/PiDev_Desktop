/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.github.plushaze.traynotification.notification.Notifications.SUCCESS;
import com.github.plushaze.traynotification.notification.TrayNotification;
import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import services.CommentaireService;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ZiwCommentaireController implements Initializable {
        @FXML
    private GridPane gridPane;
    @FXML
    private HBox pagination;
     @FXML 
    private Button voirP;
      @FXML 
    private Button AjouterP;
    
     @FXML
    private void goQ(javafx.event.ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficheCUser.fxml"));
        Parent root = loader.load();
        voirP.getScene().setRoot(root);


    }
    @FXML
    private void  addP(javafx.event.ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Commentaire.fxml"));
        Parent root = loader.load();
        AjouterP.getScene().setRoot(root);


    }
      private CommentaireService commentaireService = new CommentaireService();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         fillGridPane();
    }    
     private void fillGridPane() {
        List<Commentaire> commentaires = commentaireService.afficher();
        int row = 0;
        int column = 0;
        for (Commentaire commentaire : commentaires) {
            // Créez un Label pour afficher le titre de la question
            Label label = new Label(commentaire.toString());
            // Ajoutez le Label à la grille à la position (column, row)
            gridPane.add(label, column, row);
            // Augmentez la valeur de la colonne pour ajouter le prochain élément à droite
            column++;
            // Si la colonne atteint la valeur maximale, passez à la ligne suivante
            if (column == 2) {
                column = 0;
                row++;
            }
        }
    }
     
      
    
}
/////////////
