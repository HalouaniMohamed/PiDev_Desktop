/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.eshkili.entities.rendez_vous;
import tn.eshkili.services.RendezVous;

/**
 * FXML Controller class
 *
 * @author Mongi
 */
public class AfficheRVController implements Initializable {

    @FXML
    private TableView<rendez_vous> tableRendezVous;
    @FXML
    private TableColumn<rendez_vous, String> columnNom;
    @FXML
    private TableColumn<rendez_vous, String> columnPrenom;
    @FXML
    private TableColumn<rendez_vous, String> columnCause;
    @FXML
    private TableColumn<rendez_vous, String> columnDescription;
    @FXML
    private TableColumn<rendez_vous, Date> columnDate;
    @FXML
    private TableColumn<rendez_vous, Void> columnAction;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField searchField;
    @FXML
    private Button btn_pdf;
    @FXML
    private Button btn_tri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnCause.setCellValueFactory(new PropertyValueFactory<>("cause"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date_rv"));

        // récupérer la liste des rendez-vous et l'ajouter à la table
        RendezVous rendezvous = new RendezVous();
        ArrayList<rendez_vous> listeRendezVous = rendezvous.getListeRendezVous();
        ObservableList<rendez_vous> observableList = FXCollections.observableArrayList(listeRendezVous);
        tableRendezVous.setItems(observableList);

        columnAction.setCellFactory(param -> new TableCell<rendez_vous, Void>() {
            private final Button deleteButton = new Button("Supprimer");
            private final Button editButton = new Button("Modifier");

            {
                deleteButton.setOnAction(event -> {
                    rendez_vous rv = (rendez_vous) getTableRow().getItem();
                    // Supprimer le rendez-vous de la base de données
                    RendezVous rendezvous = new RendezVous();
                    rendezvous.supprimer(rv.getId());
                    // Supprimer le rendez-vous de la table
                    getTableView().getItems().remove(rv);
                });
                editButton.setOnAction(event -> {
    try {
        rendez_vous rv = getTableView().getItems().get(getIndex());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierRV.fxml"));
        Parent root = loader.load();
        ModifierRVController controller = loader.getController();
        controller.initData(rv);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        tableRendezVous.refresh();
    } catch (IOException ex) {
        Logger.getLogger(AfficheRVController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});

                
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(editButton, deleteButton);
                    setGraphic(hBox);

                }
            }
        });
        

        Button searchButton = new Button("Rechercher");
        searchButton.setOnAction(event -> handleSearch());

        // TODO
    }

    @FXML
    private void handleAjouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouteRV.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSearch() {
        String searchText = searchField.getText().toLowerCase();

        ObservableList<rendez_vous> allRendezVous = tableRendezVous.getItems();
        ObservableList<rendez_vous> filteredRendezVous = FXCollections.observableArrayList();

        for (rendez_vous rv : allRendezVous) {
            if (rv.getNom().toLowerCase().contains(searchText)
                    ) {
                filteredRendezVous.add(rv);
            }
        }

        tableRendezVous.setItems(filteredRendezVous);
    }

    @FXML
    private void btnGenPDF(ActionEvent event) throws DocumentException, FileNotFoundException, IOException {
    long millis = System.currentTimeMillis();
    java.sql.Date DateRapport = new java.sql.Date(millis);

    String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);
    System.out.println("Date d'aujourdhui : " + DateLyoum);

    com.itextpdf.text.Document document = new com.itextpdf.text.Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream(String.valueOf("RapportRendez_Vous "+DateLyoum + ".pdf")));
        document.open();

        // Ajouter un titre avec un style personnalisé
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
        Paragraph title = new Paragraph("Rapport détaillé des rendezVous de notre application", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Ajouter un paragraphe avec un style personnalisé
        Font paragraphFont = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
        Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les rendez vous. Derniere mise a jour : " + DateRapport, paragraphFont);
        ph1.setSpacingAfter(10);
        document.add(ph1);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        // Créer une cellule avec un style personnalisé
        Font cellFont = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase("Nom", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("prenom", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

 
        
        cell = new PdfPCell(new Phrase("Cause", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Date", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        rendez_vous r = new rendez_vous ();
        RendezVous rv = new RendezVous();
        rv.getListeRendezVous() .forEach(e -> {
            table.addCell(String.valueOf(e.getNom()));
            table.addCell(String.valueOf(e.getPrenom()));
            table.addCell(String.valueOf(e.getCause()));
            table.addCell(String.valueOf(e.getDate_rv()));

            
        });

        document.add(table);
    } catch (DocumentException | FileNotFoundException e) {
        System.out.println(e);
    }
    document.close();

    // Ouvrir le fichier PDF
     File file = new File("RapportRendez_Vous "+DateLyoum + ".pdf");
    if (file.exists()) {
        Desktop.getDesktop().open(file);
    }

    }


@FXML
private void handletri() {
    RendezVous rendezvous = new RendezVous();
    ArrayList<rendez_vous> listeRendezVous = rendezvous.getListeRendezVous();
    listeRendezVous.sort(Comparator.comparing(rendez_vous::getNom));
    tableRendezVous.setItems(FXCollections.observableArrayList(listeRendezVous));
}

    
}