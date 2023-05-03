/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Cabinet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import services.CabinetService;



/**
 * FXML Controller class
 *
 * @author Mongi
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<String, Integer> stats;
    CabinetService cs = new CabinetService();

    /**
     * Initializes the controller class.
     */
public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Cabinet> cabinet = new ArrayList<>();

      
            cabinet = (ArrayList<Cabinet>) cs.getAllCabinets();        

        System.out.println(cabinet);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        System.out.println("Entering for loop");
        for (Cabinet cabin : cabinet) {
            try {
                int reservationCount = cs.getCabinetCountByCategory(cabin.getId());
                System.out.println("Reservation count for cabin " + cabin.getNom() + ": " + reservationCount);
                series.getData().add(new XYChart.Data<>(cabin.getNom(), reservationCount));
            } catch (SQLException ex) {
                // Handle the exception
            }
        }

            stats.setTitle("Rende-vous selon Cabinet");
        stats.getXAxis().setLabel("Cabinet");
        stats.getYAxis().setLabel("nombre de Rendez-Vous");

        System.out.println(series.getData());
        // Add the series to the bar chart
        stats.getData().add(series);
    }

 
    
}
