/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.gui;

import java.io.IOException;

 import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Main extends Application {
	public static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
     
    	Main.stg = primaryStage;
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/AfficherMood.fxml"));
 
        Parent root=  loader.load();
        Scene scene= new Scene(root ,1400,700);
        primaryStage.setTitle("Bievennue");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}