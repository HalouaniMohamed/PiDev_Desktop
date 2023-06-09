package gui;

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
import entities.Commentaire;
import services.CommentaireService;
import tools.SessionManager;

public class CommentaireController implements Initializable {

    @FXML
    private Button retour;

    @FXML
    private void back(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ZiwCommentaire.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);

    }
    private TextField tfId_User;
    @FXML
    private TextField tfcommentaires_id;
    @FXML
    private TextField tfreponse;
    @FXML
    private Button ajouterbtn;
    private ListView<Commentaire> listViewCommentaire;

    private CommentaireService CommentaireService = new CommentaireService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Afficher la liste des commentaires dans la ListView

    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        // Vérifier si tous les champs sont remplis
        if (tfcommentaires_id.getText().isEmpty() || tfreponse.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis");
            alert.showAndWait();
            return;
        }

        // Vérifier si le nom d'utilisateur ne contient pas de caractères spéciaux
        String nomUtilisateur = tfreponse.getText();
        if (!nomUtilisateur.matches("[a-zA-Z0-9.,?.]+")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La réponse ne doit contenir que des lettres ou des chiffres ");
            alert.showAndWait();
            return;
        }
        int id;

        int idc;
        try {
            // Vérifier que l'ID est un entier
            idc = Integer.parseInt(tfcommentaires_id.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Numéro post invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro du post doit être un entier.");
            alert.showAndWait();
            return;
        }

        Commentaire c = new Commentaire(SessionManager.getCurrentUser().getId(), Integer.parseInt(tfcommentaires_id.getText()), tfreponse.getText());

        CommentaireService ps = new CommentaireService();

        ps.ajouter(c);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout.fxml"));

        try {
            Parent root = loader.load();

            AjoutController dc = loader.getController();
            dc.setLabel("Commentaire:" + " " + c.getReponse());

            tfreponse.getScene().setRoot(root);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}
