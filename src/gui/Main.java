package gui;

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
        
   FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/AfficheE.fxml"));
 //FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/Evenements_Client.fxml"));
 
        Parent root=  loader.load();
        Scene scene= new Scene(root ,1000,620);
        primaryStage.setTitle("Echkili");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
