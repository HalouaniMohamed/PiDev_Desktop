/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.eshkili.entities.Cabinet;
import tn.eshkili.entities.Medecin;
import tn.eshkili.entities.rendez_vous;
import tn.eshkili.services.RendezVous;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import com.twilio.exception.ApiException;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import tn.eshkili.services.CabinetService;

/**
 * FXML Controller class
 *
 * @author Mongi
 */
public class AjouteRVController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfCause;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button tfValider;
    @FXML
    private DatePicker tfDate;
    @FXML
    private ComboBox<String> cbMedecin;

    @FXML
    private ComboBox<String> cbCabinet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            RendezVous rendezvous = new RendezVous();
            ObservableList<String> listeMedecins = FXCollections.observableArrayList();
            for (Medecin medecin : rendezvous.getListeMedecins()) {
                listeMedecins.add(medecin.getNom() + " " + medecin.getPrenom());
            }

            ObservableList<String> listeCabinet = FXCollections.observableArrayList();
            for (Cabinet cabinet : rendezvous.getListeCabinets()) {
                listeCabinet.add(cabinet.getNom());
            }
            cbCabinet.setItems(listeCabinet);
            cbMedecin.setItems(listeMedecins);

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjouteRVController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void SaveRV(ActionEvent event) throws SQLException {
        RendezVous rendezvous = new RendezVous();
        try {
            CabinetService cs = new CabinetService();
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            String cause = tfCause.getText();
            String descriprion = tfDescription.getText();
            LocalDate localDate = tfDate.getValue();
            String medecin = cbMedecin.getValue();
            int idMedecin = rendezvous.getIdMedecin(medecin);
            String cabinetSelectionne = cbCabinet.getValue();



            if (nom.isEmpty() || prenom.isEmpty() || cause.isEmpty() || descriprion.isEmpty() || localDate == null) {
                // Afficher un message d'erreur si un champ est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();

                return;
            }

// Vérifier que la date est valide
            if (localDate.isBefore(LocalDate.now())) {
                // Afficher un message d'erreur si la date est antérieure à la date actuelle
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La date doit être supérieure ou égale à la date actuelle");
                alert.showAndWait();

                return;
            }
            int id_cabinet = cs.recupererBynom(cbCabinet.getValue()).getId();

            Date date_rv = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            rendez_vous rv = new rendez_vous(nom, prenom, cause, descriprion, date_rv, id_cabinet);
            rendezvous.ajouter(rv);
           final String ACCOUNT_SID = "AC5d57c3de2630a499b86b1b7781ea33fa";
           final String AUTH_TOKEN = "ce85b2e4ca30483bb9acdf358717df03";
           Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

//        Envoyer le SMS
          String toPhoneNumber = "+21624660566"; // Numéro de téléphone de destination
          String fromPhoneNumber = "+16813233462"; // Numéro de téléphone Twilio
          String messageBody = "votre rendez-vous est pris avec succées <3 "; // Contenu du message
          Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), messageBody).create();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficheRV.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouteRVController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ApiException ex) {
    Logger.getLogger(AjouteRVController.class.getName()).log(Level.SEVERE, null, ex);
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Erreur lors de l'envoi du SMS: " + ex.getMessage());
    alert.showAndWait();
} catch (TwilioException ex) {
    Logger.getLogger(AjouteRVController.class.getName()).log(Level.SEVERE, null, ex);
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Erreur lors de l'envoi du SMS: " + ex.getMessage());
    alert.showAndWait();
    }

}}
