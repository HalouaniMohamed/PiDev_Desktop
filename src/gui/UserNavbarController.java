/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class UserNavbarController implements Initializable {

    @FXML
    private Button p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void redirectToHome(ActionEvent event) {
    }

    @FXML
    private void redirectToNews(ActionEvent event) {
    }

    @FXML
    private void redirectToForum(ActionEvent event) {
    }

    @FXML
    private void redirectToRv(ActionEvent event) {
    }

    @FXML
    private void redirectToProducts(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProducts.fxml"));
        try {
            Parent root = loader.load();
            p.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToMood(ActionEvent event) {
    }

    @FXML
    private void redirectToEvents(ActionEvent event) {
    }

    @FXML
    private void redirectToCart(ActionEvent event) {
    }

}
