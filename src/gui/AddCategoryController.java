/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Category;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.CategoryService;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class AddCategoryController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextArea tfDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Hello");
    }

    @FXML
    private void addCategory(ActionEvent event) {

        //check that the name textfield is not empty
        if (tfName.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);

            aler.setContentText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else if (tfName.getText().length() < 3) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setHeaderText(null);
            aler.setTitle("Erreur");
            aler.setContentText("Le nom doit depasser 3 caracteres");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();

        } else {
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            Category c = new Category(tfName.getText(), tfDescription.getText(), date, date);
            CategoryService cs = new CategoryService();
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return cs.isUnique(c);
                }
            };

            task.setOnSucceeded(e -> {
                boolean isUnique = task.getValue();
                if (!isUnique) {
                    Alert aler = new Alert(Alert.AlertType.ERROR);
                    aler.setHeaderText(null);
                    aler.setTitle("Erreur");
                    aler.setContentText("Le nom existe déjà");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    aler.getButtonTypes().setAll(okButton);
                    aler.showAndWait();
                } else {
                    cs.add(c);

                    // on success show alert that displays a success message then empty the textfields
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Catégorie ajoutée");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
                    okBtn.setOnAction(evt -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesList.fxml"));
                        try {
                            Parent root = loader.load();
                            tfName.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
                    alert.showAndWait();
                    tfName.setText(null);
                    tfDescription.setText(null);
                }
            });

            new Thread(task).start();
        }
    }

}
