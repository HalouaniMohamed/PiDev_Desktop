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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class SideMenuController implements Initializable {

    @FXML
    private TextField tfName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showCategories(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesList.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addCategory(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void showProducts(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminProductsList.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addProduct(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToAddE(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddE.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToUsers(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User_List.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToPost(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPost.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToCommentaire(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminCommentaire.fxml"));
        try {
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
