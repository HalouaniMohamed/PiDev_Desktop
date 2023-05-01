/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenements;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

 import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.EvenementsService;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheEController implements Initializable {

    @FXML
    private AnchorPane id;
    @FXML
    private TableView<Evenements> categoriesView;
    @FXML
    private TableColumn<Evenements, String> tfNom;
    @FXML
    private TableColumn<Evenements, String>tfLieu;
    @FXML
    private TableColumn<Evenements, String> tfDate;
    @FXML
    private TableColumn<Evenements, String> tfDescription;
    @FXML
    private TableColumn<Evenements, String> tfNbr;
    @FXML
    private TableColumn<Evenements, String> tfType;
    ObservableList<Evenements> listeB = FXCollections.observableArrayList();
    @FXML
    private Button btn;

    @FXML
    private Button btndelete;

    public void show(){
    	EvenementsService bs=new EvenementsService();
    listeB=bs.afficher();
    tfNom.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
    tfLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_evenement"));
    tfDate.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));
     
       

    tfDescription.setCellValueFactory(new PropertyValueFactory<>("description_evenement"));
    tfNbr.setCellValueFactory(new PropertyValueFactory<>("nbr_de_places"));
    tfType.setCellValueFactory(new PropertyValueFactory<>("type"));
    
 
    categoriesView.setItems(listeB);
    }
    
   


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    show();
}

    @FXML
    private void Modifier(javafx.event.ActionEvent event) { 
        Evenements selectedLN =  categoriesView.getSelectionModel().getSelectedItem();
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
            dialog.setTitle("Modifier un evenement");
            dialog.setHeaderText("Modifier les champs de l'evenement");
            dialog.setContentText("Nom de l'evenement:");

            // Set the default value of the input field to the current event name.
            dialog.getEditor().setText(selectedLN.getNom_evenement());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                // Update the event name with the new value.
                selectedLN.setNom_evenement(result.get());
                // TODO: Update the other event details using similar steps.

                EvenementsService bs = new EvenementsService();
                
                bs.modifier(selectedLN);
                // Show a confirmation alert.
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("L'evenement a ete modifie avec succe");
                alert.setContentText("Les modifications ont ete enregistrees.");
                alert.showAndWait();
            }
            
        }
        categoriesView.refresh();
 
        
    }

    @FXML
    private void Supprimer(javafx.event.ActionEvent event) throws SQLException {
        
        Evenements selectedLN =  categoriesView.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer  ");
            alert.setContentText("Veuillez selectionner pour supprimer !");
            alert.showAndWait();
        } else {
            EvenementsService bs = new EvenementsService();
            System.out.println(selectedLN.getId());
             List<Reservation> list = new EvenementsService().getR(selectedLN.getId());
            
            for(Reservation r : list){
         sendMail(r);
                System.out.println(r.getEmail());
            }
            bs.supprimer(selectedLN.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("Evenements supprime!");
            alert.showAndWait();

            // Actualiser le TableView
            show();
           
            
            

        }
    }
        public void sendMail( Reservation r) {
        // Set the SMTP host and port for sending the email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "meriem.bouchahoua@esprit.tn";
        String password = "223JFT442893732971";

        // Set the properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption

        // Create a new email session using the specified properties
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message msg = new MimeMessage(session);

            // Set the "From" address for the email
            // msg.setFrom(new InternetAddress("ahmed.benabid2503@gmail.com"));
            // Add the "To" address for the email (including the recipient's name)
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(r.getEmail()));

            // Set the subject and body text for the email
            msg.setSubject("Annulation d'evenements");
            msg.setText("Bonjour , nous sommes desolés de vous informer que , l'"+r.getE().getNom_evenement()+" a été annulé , merci pour votre compréhension");
            // Create an alert to notify the user that the email was sent successfully

            

            // Send the email
       
               

                Transport.send(msg);
 

          
                // Close the dialog and do nothing
              

            // Print a message to the console to indicate that the email was sent successfully
        } catch (AddressException e) {
            // Create an alert to notify the user that there was an error with the email address
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
    @FXML 
    private Button ajoutE;
    
    
     @FXML
    private void AjouterE(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AddE.fxml"));
        Parent root = loader.load();
        ajoutE.getScene().setRoot(root);

    }
 
    @FXML 
    private Button voirR;
    
     @FXML
    private void goR(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficheReservation.fxml"));
        Parent root = loader.load();
        voirR.getScene().setRoot(root);

    }
    
}
