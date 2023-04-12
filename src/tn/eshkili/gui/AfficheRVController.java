/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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

        {
            deleteButton.setOnAction(event -> {
    rendez_vous rv = (rendez_vous) getTableRow().getItem();
                // Supprimer le rendez-vous de la base de données
                RendezVous rendezvous = new RendezVous();
                rendezvous.supprimer(rv.getId());
                // Supprimer le rendez-vous de la table
                getTableView().getItems().remove(rv);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(deleteButton);
            }
        }
    });
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

    
    
    
    

}

