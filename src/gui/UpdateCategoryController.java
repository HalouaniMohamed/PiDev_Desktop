/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private String name;
    private String description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hello from update controller");
    }

    @FXML
    private void updateCategory(ActionEvent event) {
    }

    public void setTfName(String name) {
        this.tfName.setText(name);
    }

    public void setTfDescription(String description) {
        this.tfDescription.setText(description);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
