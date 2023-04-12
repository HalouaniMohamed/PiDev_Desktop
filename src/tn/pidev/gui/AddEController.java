package tn.pidev.gui;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.pidev.entites.Evenements;
import tn.pidev.services.EvenementsService;
 
/**
 * FXML Controller class
 *
 */
public class AddEController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextField tfCapacity;

    @FXML
    private TextField tfType;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tfHour;
     
    @FXML
    private Button submitButton;
    @FXML
	private Label btnChoisir;
    private File file;
    @FXML
	private TextField urlTF;
	private Stage stage;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void addEvent(ActionEvent event) {

        // Check that all text fields have values
        if (tfName.getText().isEmpty() || tfDescription.getText().isEmpty() || tfLocation.getText().isEmpty()
                || tfCapacity.getText().isEmpty() || urlTF.getText().isEmpty() || tfType.getText().isEmpty()
                || tfHour.getText().isEmpty() || tfDate.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("One or more fields are empty!");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        } else {
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Use LocalDate and LocalTime instead of Date and Time classes for easier manipulation of date and time values
            LocalDate localDate = tfDate.getValue();
            LocalTime localTime = LocalTime.parse(tfHour.getText().trim());

            Evenements eventObj = new Evenements(Integer.parseInt(tfCapacity.getText()), tfName.getText(),
                    tfLocation.getText(), tfDescription.getText(), tfType.getText(),
                    Date.valueOf(localDate), Time.valueOf(localTime));
            System.out.println(file.getPath());
            eventObj.setImage(file.getAbsolutePath());

            // Call the EventService to add the event to the database
            EvenementsService eventService = new EvenementsService();
            eventService.ajouter(eventObj);

            // Show success message and clear text fields
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Event added successfully");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
            tfName.setText("");
            tfDescription.setText("");
            tfLocation.setText("");
            tfCapacity.setText("");
            tfDate.setValue(null);
            tfType.setText("");
            tfHour.setText("");
            urlTF.setText("");

        }
    }
    
	@FXML
	private void importer(ActionEvent event) {
	  
        
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionnez un fichier PNG");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
    File fichierSelectionne = fileChooser.showOpenDialog(stage);

    if (fichierSelectionne != null) {
        urlTF.setText(fichierSelectionne.getName());
        file = fichierSelectionne;
       
    }
	}
    
 
}
