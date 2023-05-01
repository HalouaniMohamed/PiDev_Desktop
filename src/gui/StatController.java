/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenements;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import services.EvenementsService;


/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class StatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            statistique();
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     @FXML
    private void retour(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficheReservation.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);

    }
     @FXML 
     private Button retour;
     
     
      @FXML
    private BarChart<String, Number> stat;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    
    public void statistique() throws SQLException {
       EvenementsService ms = new EvenementsService();

        List<Evenements> packs = new EvenementsService().afficher();
       

        // Créer les axes pour le graphique
       
     
        xAxis.setLabel("Nom des Evènements");
        
        yAxis.setLabel("nombre des des participants par evènements");
    yAxis.setTickLabelGap(5);
xAxis.setTickLabelGap(5);
yAxis.setAutoRanging(false);

yAxis.setTickUnit(1);

        // Créer la série de données à afficher
        XYChart.Series series = new XYChart.Series();
        series.setName("nombre des des participants par evènements");
        
        
        for (Evenements pack : packs) {
            series.getData().add(new XYChart.Data<>(pack.getNom_evenement(), ms.getNbr_place(pack.getId())));
        }
        

        
        stat.getData().add(series);
        stat.setCategoryGap(5);
       
     
    }
    
    
    
    
}
