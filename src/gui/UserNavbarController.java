/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.worshop.controllers.User_AddController;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tools.SessionManager;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class UserNavbarController implements Initializable {

    @FXML
    private Button p;
    @FXML
    private Button loginBtn;
    @FXML
    private Button singupBtn;
    @FXML
    private HBox navbar;
    private User currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            navbar.getChildren().remove(loginBtn);
//            navbar.getChildren().remove(singupBtn);
            singupBtn.setText("Déconnenxion");
            Image image = new Image("/images/avatar.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            imageView.setOnMouseClicked(event -> {
                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("Front_Profile.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
                    //showAlert("Error loading");
                }
            });

            Label userName = new Label(currentUser.getFull_name());
            userName.setAlignment(Pos.CENTER_LEFT);
            userName.setMaxWidth(Double.MAX_VALUE);

            HBox avatar = new HBox(imageView, userName);
            avatar.setSpacing(8);
            avatar.setAlignment(Pos.CENTER);
            navbar.getChildren().addAll(avatar);
        }
    }

    @FXML
    private void redirectToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void redirectToNews(ActionEvent event) {
    }

    @FXML
    private void redirectToForum(ActionEvent event) {
        User currentUser = SessionManager.getCurrentUser();
        System.out.println("mmmmmmmmmmmmmmm");
        System.out.println(currentUser.getRoles());
        if (currentUser.getRoles().contains("[ROLE_MEDECIN]")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ZiwCommentaire.fxml"));
            try {
                Parent root = loader.load();
                p.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ziw.fxml"));
            try {
                Parent root = loader.load();
                p.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void redirectToRv(ActionEvent event) {
        User currentUser = SessionManager.getCurrentUser();
        System.out.println("mmmmmmmmmmmmmmm");
        System.out.println(currentUser.getRoles());
        if (currentUser.getRoles().contains("[ROLE_MEDECIN]")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheRV.fxml"));
            try {
                Parent root = loader.load();
                p.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (currentUser.getRoles().contains("[ROLE_PATIENT]")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouteRV.fxml"));
            try {
                Parent root = loader.load();
                p.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void redirectToProducts(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProductsList.fxml"));
        try {
            Parent root = loader.load();
            p.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToMood(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterMood.fxml"));
        try {
            Parent root = loader.load();
            p.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToEvents(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenements_Client.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void redirectToCart(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCart.fxml"));
        try {
            Parent root = loader.load();
            p.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToJournal(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterJournal.fxml"));
        try {
            Parent root = loader.load();
            p.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Front_Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void redirectToSignup(ActionEvent event) {
        if (currentUser == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Front_Registration.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            SessionManager.cleanUserSession();
            SessionManager.setCurrentUser(null);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
