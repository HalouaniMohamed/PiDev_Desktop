/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.echkili.gui;



/**
 *
 * @author rayen
 */
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Home extends Application {
    
   
    @Override
    public void start(Stage primaryStage) {
        try {
      
            Parent root =FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("ECHKILI");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}