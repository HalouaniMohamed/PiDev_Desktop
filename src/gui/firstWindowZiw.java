/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class firstWindowZiw extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try {
            Parent root = FXMLLoader.load(getClass().getResource("Post.fxml"));
           // Parent r = FXMLLoader.load(getClass().getResource("Commentaire.fxml"));
            
            
            Scene scene = new Scene(root,830,650);
            
            primaryStage.setTitle("Forum");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
