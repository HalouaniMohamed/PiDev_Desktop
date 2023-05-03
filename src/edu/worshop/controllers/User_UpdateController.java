/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import services.ServiceUser;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class User_UpdateController implements Initializable {

    ServiceUser su = new ServiceUser();

    @FXML
    private TextField fullnameF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField passwordF;
    @FXML
    private TextField adreeseF;
    int index = -1;
    @FXML
    private DatePicker dateNaissanceField;
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<String> roleu;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        fullnameF.setText(String.valueOf(User_ListController.U.getFull_name()));
        emailF.setText(String.valueOf(User_ListController.U.getEmail()));
        passwordF.setText(String.valueOf(User_ListController.U.getPassword()));
        adreeseF.setText(String.valueOf(User_ListController.U.getAddress()));
        //dateNaissanceField.setValue(User_ListController.U.getDate_naissance().toLocalDate());
        roleu.getItems().addAll("ROLE_PATIENT", "ROLE_MEDECIN");

        //Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        //label.setFont(font);
    }

    @FXML
    private void EditUser2(ActionEvent event) {
        // Retrieve the id of the Matches to update
        int id = User_ListController.U.getId();

        // Retrieve the new values from the text fields
        String full_name = fullnameF.getText();
        String email = emailF.getText();
        String address = adreeseF.getText();
        String password = passwordF.getText();
        Date date = Date.valueOf(dateNaissanceField.getValue());
        String role = roleu.getValue();
        java.sql.Date dateNaissance = new java.sql.Date(Date.valueOf(dateNaissanceField.getValue()).getTime());

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email invalide", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            // Show an error message and return
            Alert alert = new Alert(AlertType.ERROR, "Mot de passe doit contenir 8 characteres", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        // Check if address is not empty
        if (address.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Adresse ne peut pas etre vide", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Check if full name is not empty
        if (full_name.isEmpty()) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Pseudo ne peut pas etre vide", ButtonType.OK);
            alert.showAndWait();
            return;

        }
        /* if (su.emailExist(email)) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email already exists", ButtonType.OK);
            alert.showAndWait();
            return;
        }*/
        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Mot de passe doit contenir 8 characteres", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Convert the date from the DatePicker to a Date object
        // Date date = Date.valueOf(dateNaissanceField.getValue());
        //String img_user = MyConnection.getImage_Name();
        // Update the User object with the new values
        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setFull_name(full_name);
        updatedUser.setEmail(email);
        updatedUser.setPassword(password);
        updatedUser.setAddress(address);
        updatedUser.setDate_naissance(date);
        // updatedUser.setImg_user(img_user);
        List<String> roleList = new ArrayList<>();
        roleList.add(role);
        updatedUser.setRoles(roleList);
        su.update(updatedUser);

        // Show a success message
        Alert alert = new Alert(AlertType.INFORMATION, "Utilisateur modifé", ButtonType.OK);
        alert.showAndWait();
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/User_List.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_ListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void chooseImage(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Select an image file");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
//        );
//        Window window = ((Node) event.getTarget()).getScene().getWindow();
//        File selectedFile = fileChooser.showOpenDialog(window);
//        if (selectedFile != null) {
//            try {
//                // Create a directory called "upload" if it doesn't exist
//                File uploadDir = new File("upload");
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//
//                // Copy the selected file to the "upload" directory
//                String fileName = selectedFile.getName();
//                File destFile = new File("upload/" + fileName);
//                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                // Set the ImageView's image to the selected image
//                Image image = new Image(destFile.toURI().toString());
//                imageView.setImage(image);
//                //Save the image name in img_Saver
//                MyConnection.setImage_Name(fileName);
//            } catch (IOException ex) {
//                Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

}
