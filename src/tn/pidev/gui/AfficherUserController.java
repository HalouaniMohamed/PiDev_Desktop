package tn.pidev.gui;
 
import java.net.URL;

import java.util.List;

import java.util.ResourceBundle;

 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import tn.pidev.entites.Evenements;
import tn.pidev.services.EvenementsService;

public class AfficherUserController implements Initializable {
 
    @FXML private ListView<Evenements> listView;

 	    public void initialize(URL location, ResourceBundle resources) {
 	       EvenementsService es = new EvenementsService();
 	        List<Evenements> evenements = es.afficher();

 	       listView.setCellFactory((Callback<ListView<Evenements>, ListCell<Evenements>>) new Callback<ListView<Evenements>, ListCell<Evenements>>() {
 	    	    public ListCell<Evenements> call(ListView<Evenements> param) {
 	    	        return new ListCell<Evenements>() {

 	    	            @Override
 	    	            protected void updateItem(Evenements item, boolean empty) {
 	    	                super.updateItem(item, empty);

 	    	                if (empty || item == null) {
 	    	                    setText(null);
 	    	                    setGraphic(null);
 	    	                } else {
 	    	                    
 	    	                    HBox hbox = new HBox();
 	    	                    hbox.setAlignment(Pos.CENTER_LEFT);
 	    	                    hbox.setSpacing(10);

 	    	                    
 	    	                    ImageView imageView = new ImageView(new Image("file:///"+item.getImage()));
 	    	                    imageView.setFitHeight(50);
 	    	                    imageView.setFitWidth(50);

 	    	                    Label nomLabel = new Label(item.getNom_evenement());
 	    	                   nomLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

 	    	                    Label typeLabel = new Label(item.getType());
 	    	                    typeLabel.setStyle("-fx-font-size: 14;");
                                    
                                    Label DescriptionLabel = new Label(item.getDescription_evenement());
 	    	                    typeLabel.setStyle("-fx-font-size: 14;");

 	    	                    Label lieuLabel = new Label(item.getLieu_evenement());
 	    	                    lieuLabel.setStyle("-fx-font-size: 14;");

 	    	                    Label dateLabel = new Label(item.getDate_evenement().toString());
 	    	                    dateLabel.setStyle("-fx-font-size: 14;");

 	    	                    
 	    	                    hbox.getChildren().addAll(imageView, nomLabel, typeLabel, lieuLabel, dateLabel ,DescriptionLabel );

 	    	                    // Afficher la HBox comme contenu de la cellule
 	    	                    setGraphic(hbox);
 	    	                }
 	    	            }
 	    	        };
 	    	    }
 	    	});

 	    	
  	    	listView.getItems().addAll(evenements);

 	    }
	 
	}
 	   
 
