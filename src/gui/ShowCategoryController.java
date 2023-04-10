/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class ShowCategoryController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNameLabel(String text) {
        this.nameLabel.setText(text);
    }

    public void setDescriptionLabel(String text) {
        this.descriptionLabel.setText(text);
    }

}
