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
public class UpdateCategoryController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextArea tfDescription;
    private Category category;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void updateCategory(ActionEvent event) {
        System.out.println("method" + category);
        if (tfName.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else {
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            CategoryService cs = new CategoryService();
            category.setCategoryName(tfName.getText());
            category.setDescription(tfDescription.getText());
            category.setUpdatedAt(date);
            cs.update(category);

            // on success show alert that displays a success message then empty the textfields
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Catégorie modifié");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
            okBtn.setOnAction(e -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesList.fxml"));

                try {
                    Parent root = loader.load();

                    tfName.getScene().setRoot(root);
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
                // Redirect to the showProduct interface
                // Code to redirect here
            });
            alert.showAndWait();
            tfName.setText(null);
            tfDescription.setText(null);

        }
    }

    public void setTfName(String name) {
        this.tfName.setText(name);
    }

    public void setTfDescription(String description) {
        this.tfDescription.setText(description);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
