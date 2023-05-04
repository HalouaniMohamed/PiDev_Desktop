/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.SessionManager;

/**
 *
 * @author Mongi
 */
public class FirstWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        User currentUser = SessionManager.getCurrentUser();
        System.out.println(currentUser.getRoles());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajouteRV.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setTitle("pris d'un RendezVous !");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FirstWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
