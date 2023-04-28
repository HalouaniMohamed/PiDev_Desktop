/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tn.eshkili.entities.Mood;
import tn.eshkili.services.Mood1;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherMoodController implements Initializable {

    
    
    @FXML
    private ListView<Mood> Moodview;
    static int id,user_id,mood_id;   
    static String mood,description;
    static Mood M = new Mood();
    private TableColumn<Mood, Integer> tfid;
    private TableColumn<Mood, Integer> tfuser;
    private TableColumn<Mood, Integer> tfmoodid;
    private TableColumn<Mood, String> tfmood;
    private TableColumn<Mood, String> tfdesc;
     ObservableList<Mood> listeB = FXCollections.observableArrayList();
    @FXML
    private Button buttmod;
    @FXML
    private Button buttsupp;
    @FXML
    private Button buttstat;
    @FXML
    private TextField searchField;

    static Mood E = new Mood();
        private ObservableList<Mood> moodList;
    @FXML
    private Button butpdf;


    /**
     * Initializes the controller class.
     */
    
        
          
 

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // show();
        ListView<Mood> list1 = Moodview;
    Mood1 inter = new Mood1();
    List<Mood> list2 = inter.afficherMood();
    moodList = FXCollections.observableArrayList(list2);
for (int i = 0; i < list2.size(); i++) {
    Mood E = list2.get(i);
    list1.getItems().add(E); 
}    
        
  list1.setCellFactory(param -> new ListCell<Mood>() {
            @Override
            protected void updateItem(Mood item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getId() + "   |   " + item.getUser_id() + "   |   " + item.getMood_id() + "   |   " + item.getMood() + "   |   " + " (" + item.getDescription() + ")");
                }
            }
        });

        // Ajouter une fonction de recherche
        FilteredList<Mood> filteredList = new FilteredList<>(moodList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(e -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ( String.valueOf(e.getUser_id()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par nom complet

                } else if (e.getMood().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par date de naissance

                }

                return false; // Aucune correspondance trouvée
            });
        });

        SortedList<Mood> sortedList = new SortedList<>(filteredList);
        list1.setItems(sortedList);
    }    

    @FXML
    private void modifierMood(javafx.event.ActionEvent event) {
        
        

    
//     private void showAlert(String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    
//    }
        
         Mood selectedLN =  (Mood) Moodview.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier  ");
            alert.setContentText("Veuillez selectionner pour modifier !");
            alert.showAndWait();
        }else {
            // Show an input dialog to get the new event details.
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier un mood");
            dialog.setHeaderText("Modifier les champs du mood");
            dialog.setContentText("Nom de mood:");

            // Set the default value of the input field to the current event name.
            dialog.getEditor().setText(selectedLN.getMood());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                // Update the event name with the new value.
                selectedLN.setMood(result.get());
                // TODO: Update the other event details using similar steps.

                Mood1 bs = new Mood1();
                
                bs.modifierMood(selectedLN);
                // Show a confirmation alert.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Mood a ete modifie avec succe");
                alert.setContentText("Les modifications ont ete enregistrees.");
                alert.showAndWait();
            }
            
        }
        Moodview.refresh();
 
    }

    @FXML
    private void supprimermood(ActionEvent event) {
        
                
        Mood selectedLN =  (Mood) Moodview.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer  ");
            alert.setContentText("Veuillez selectionner pour supprimer !");
            alert.showAndWait();
        } else {
            Mood1 bs = new Mood1();
            System.out.println(selectedLN.getId());
            bs.supprimerMood(selectedLN.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information");
            alert.setHeaderText(null);
            alert.setContentText("Mood supprime!");
            alert.showAndWait();

            // Actualiser le TableView
            //show();
        }
    }

    @FXML
    private void stat(ActionEvent event) {
        // Create a map to store the frequency of each type
        Map<String, Integer> typeFrequency = new HashMap<>();

        // Loop through the items in the TableView
        for (Mood o : Moodview.getItems()) {
            //int points = o.getPoints();
            String mood = o.getMood();

            if (typeFrequency.containsKey(mood)) {
                typeFrequency.put(mood, typeFrequency.get(mood) + 1);
            } else {
                typeFrequency.put(mood, 1);
            }
        }
    
        // Create a PieChart data set
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String nom: typeFrequency.keySet()) {
            int frequency = typeFrequency.get(nom);
            double percentage = (double) frequency / Moodview.getItems().size() * 100;

            String percentageText = String.format("%.2f%%", percentage);


            PieChart.Data slice = new PieChart.Data("Mood" + " " + percentageText, frequency);
            pieChartData.add(slice);
        }


    
         // Create a PieChart with the data set
        PieChart chart = new PieChart(pieChartData);
     
        // Show percentage values in the chart's tooltip
        for (final PieChart.Data data : chart.getData()) {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(String.format("%.2f%%", (data.getPieValue() / Moodview.getItems().size() * 200)));
            Tooltip.install(data.getNode(), tooltip);
        }

        // Show the chart in a new window
        Scene scene = new Scene(chart);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       }

    @FXML
    private void pdf(ActionEvent event) {
          Document document = new Document(PageSize.A4);
        Color headerColor = Color.web("#0692a1");
        Color cellColor = Color.web("#ff7a4a");
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 12);

        try {
            PdfWriter.getInstance(document, new FileOutputStream("user.pdf"));

            document.open();

            Paragraph paragraph = new Paragraph("les produits");
           // paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            document.add(Chunk.NEWLINE);

            PdfPTable pdfTable = new PdfPTable(2);

            ObservableList<Mood> selectedProduits = Moodview.getSelectionModel().getSelectedItems();

            pdfTable.addCell("Nom du champ");
            pdfTable.addCell("Valeur");

            for (Mood mood : selectedProduits) {
//                pdfTable.addCell("ID");
//                pdfTable.addCell(String.valueOf(produit.getId()));

                pdfTable.addCell("Mood");
                pdfTable.addCell(mood.getMood());

                

                pdfTable.completeRow(); // Add a new row for each selected item
            }

            document.add(pdfTable);

            document.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Export PDF");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier PDF a été généré avec succès !");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
        
    }

  
    

   
    
