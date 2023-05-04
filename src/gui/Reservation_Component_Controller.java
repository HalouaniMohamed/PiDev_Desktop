package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Evenements;
import entities.Reservation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.stage.FileChooser;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.EvenementsService;
import services.ReservationService;
import tools.SessionManager;

public class Reservation_Component_Controller implements Initializable {

    private Evenements_Client_Controller controller;
    @FXML
    private Label _date;

    @FXML
    private Label _description;

    @FXML
    private Label _emplacement;

    @FXML
    private Label _nom;

    @FXML
    private ImageView _pic;

    @FXML
    private Label _places;

    @FXML
    private Slider _qunatite;

    @FXML
    private Label _time;

    @FXML
    private Label _type;
    private Evenements event;
    private String userEmail;

    @FXML
    void close(ActionEvent event) {
        controller.clear();
    }
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public void set(Evenements_Client_Controller evenements_client_controller, Evenements event) {
        this.controller = evenements_client_controller;
        this.event = event;
        _qunatite.setMax(event.getNbr_de_places());
        _pic.setImage(new Image("file:///" + event.getImage()));
        _nom.setText(event.getNom_evenement());
        _places.setText(String.valueOf(event.getNbr_de_places()));
        _time.setText(event.getHeure().toString());
        _date.setText(formatter.format(event.getDate_evenement()));
        _description.setText(event.getDescription_evenement());
        _emplacement.setText(event.getLieu_evenement());
        _pic.setImage(new Image("file:///" + event.getImage()));
        _type.setText(event.getType());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip tooltip = new Tooltip();
        userEmail = SessionManager.getCurrentUser().getEmail();

        try {
            _qunatite.setOnMouseReleased(event -> {
                tooltip.setText("Value: " + Double.valueOf(_qunatite.getValue()).intValue());
                tooltip.show(_qunatite, event.getScreenX(), event.getScreenY());
            });
            _qunatite.setOnMouseDragged(event -> {
                tooltip.setText("Value: " + Double.valueOf(_qunatite.getValue()).intValue());
                tooltip.show(_qunatite, event.getScreenX(), event.getScreenY());
            });
            _qunatite.setOnMouseExited(event -> {
                tooltip.hide();
            });

        } catch (Exception ignored) {
        }

    }
    private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private final Pattern pattern = Pattern.compile(regex);

    @FXML
    void valider(ActionEvent event) throws Exception {
//        Matcher matcher = pattern.matcher(_email.getText());
//        if (!matcher.matches()) {
//            new Alert(Alert.AlertType.WARNING, "E-mail Invalide", ButtonType.OK).show();
//        } else
        if ((userEmail == null) || (userEmail == "")) {
            new Alert(Alert.AlertType.WARNING, "Vous devez vous connecter", ButtonType.OK).show();

        }
        if (this.event.getNbr_de_places() == 0) {

            new Alert(Alert.AlertType.WARNING, "Pas de places disponible pour  cet evenement!", ButtonType.OK).show();

        } else if (_qunatite.getValue() > this.event.getNbr_de_places()) {
            new Alert(Alert.AlertType.WARNING, "Pas de places disponible pour  cet evenement!", ButtonType.OK).show();

        } else {
            Reservation r = new Reservation(Double.valueOf(_qunatite.getValue()).intValue(), userEmail, this.event);
            new ReservationService().ajouter(r);
            this.event.setNbr_de_places(this.event.getNbr_de_places() - Double.valueOf(_qunatite.getValue()).intValue());
            sendMail(this.event, r);
            generatePDF(r);
            new EvenementsService().modifier(this.event);
            new Alert(Alert.AlertType.INFORMATION, "Reservation Validée!", ButtonType.OK);
            controller.clear();
            controller.Afficher(null);
        }
    }
    String username = "mohamed.halouani@esprit.tn";
    String password = "MedHal@@99";

    public void sendMail(Evenements ee, Reservation r) {
        // Set the SMTP host and port for sending the email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "mohamed.halouani@esprit.tn";
        String password = "MedHal@@99";

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
            msg.setSubject("Confirmation de réservation");
            msg.setText("Salut , Votre Réservation pour l'evenement " + ee.getNom_evenement() + " a été effectuée , avec un nombre de place " + r.getNombre_de_place_areserver() + "."
                    + "Veuillez nous contacter en cas d'annulation et merci.");
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

    public void generatePDF(Reservation c) throws Exception {
        Document document = new Document();
        String fileName = "Reservation" + c.getId() + ".pdf";

        // Ouvrir une fenêtre de choix de fichier pour sélectionner l'emplacement où enregistrer le fichier PDF
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Reçu de votre resrevation");
        fileChooser.setInitialFileName(fileName);
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            // Enregistrer le fichier PDF à l'emplacement sélectionné
            PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
            document.open();

            // Ajouter les informations du ticket
            com.itextpdf.text.Font fontTitre = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 24, com.itextpdf.text.Font.BOLD);
            Paragraph titre = new Paragraph("Reçu d' accés à l'événement ", fontTitre);
            titre.setAlignment(Element.ALIGN_CENTER);
            titre.setSpacingAfter(20f);
            document.add(titre);

//     Image image = new Image(c.getImage());
//      image.setAlignment(Element.ALIGN_CENTER);
//       image.scaleAbsolute(400, 200); // ajuster la taille de l'image en points
//        document.add((Element) image);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18); // créer une police avec une taille de 12 points

//        Paragraph info = new Paragraph();
//        info.setAlignment(Element.ALIGN_CENTER);
//        info.setSpacingBefore(20f);
//        info.setSpacingAfter(10f);
//        document.add(info);
//Paragraph info = new Paragraph("Reservation numéro: "+c.getId()+".", font);
//        info.setSpacingAfter(5f);
//        document.add(info);
            Paragraph ref = new Paragraph("Nombre de place réservées: " + c.getNombre_de_place_areserver() + ".", font);
            ref.setSpacingAfter(5f);
            document.add(ref);

            Paragraph date = new Paragraph("Evènement: " + c.getE().getNom_evenement() + ".", font);
            date.setSpacingAfter(5f);
            document.add(date);

            Paragraph lieu = new Paragraph("Lieu d'evenement: " + c.getE().getLieu_evenement() + ".", font);
            lieu.setSpacingAfter(5f);
            document.add(lieu);

            com.itextpdf.text.Font font1 = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12);

            Paragraph aa = new Paragraph("Un email de confirmation a été envoyé à votre adresse mail,veuillez imprimer ce reçu et merci. ", font1);
            aa.setAlignment(Element.ALIGN_CENTER);
            aa.setSpacingAfter(5f);
            document.add(aa);

            document.close();

            // Ouvrir le fichier PDF une fois qu'il est enregistré
            Desktop.getDesktop().open(selectedFile);
        }
    }

}
