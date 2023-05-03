/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import entities.User;
import services.ServiceUser;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;

import com.restfb.exception.FacebookException;
import tools.SessionManager;
//import com.restfb.types.User;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class Front_LoginController implements Initializable {

    @FXML
    private TextField emaillogin;
    @FXML
    private TextField passwordlogin;
    @FXML
    private Label LoginLabel;
    @FXML
    private Hyperlink forgetPasswordLink;
    @FXML
    private Hyperlink newacc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceUser ServiceUser = new ServiceUser();
        //Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 18);
    }

    @FXML
    private void connexionF(ActionEvent event) throws IOException {
        String email = emaillogin.getText();
        String password = passwordlogin.getText();

        // create an instance of the UserService class
        ServiceUser userService = new ServiceUser();
        // call the authenticate method on the UserService instance
        User loggedInUser = userService.authenticate(email, password);
        SessionManager.setCurrentUser(loggedInUser);
//        SessionManager.setEmail(loggedInUser.getEmail());
//
//        SessionManager.setId(loggedInUser.getId());SessionManager.setCurrentUser(loggedInUser);
//        SessionManager.setEmail(loggedInUser.getEmail());

//        SessionManager.setRoles(loggedInUser.getRoles().get(0));
        if (loggedInUser != null) {
            List<String> roles = loggedInUser.getRoles();
            if (roles.contains("[ROLE_ADMIN]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/User_List.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (roles.contains("[ROLE_MEDECIN]")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/Home.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (roles.contains("[ROLE_PATIENT]")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/UserCart.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            displayErrorMessage();
        }
    }

    public void seConnecterAvecFacebook() throws UnsupportedEncodingException, SQLException, IOException, ParseException, FacebookException {
//    String appId = "YOUR_APP_ID";
//    String appSecret = "YOUR_APP_SECRET";
//    String redirectUrl = "YOUR_REDIRECT_URL";
//    String code = "THE_CODE_FROM_FACEBOOK";
//    String accessTokenUrl = "https://graph.facebook.com/v12.0/oauth/access_token?client_id=" + appId + "&redirect_uri="
//            + URLEncoder.encode(redirectUrl, "UTF-8") + "&client_secret=" + appSecret + "&code=" + code;
//
//    // Step 1: Get the Access Token
//    String accessToken = null;
//    try {
//        String response = sendGetRequest(accessTokenUrl);
//        JSONObject json = new JSONObject(response);
//        accessToken = json.getString("access_token");
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//
//    // Step 2: Use the Access Token to fetch User Data
//    FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
//    User user = null;
//    try {
//        user = fbClient.fetchObject("me", User.class);
//        System.out.println("Name: " + user.getName());
//    } catch (FacebookException e) {
//        throw e; // rethrow the exception to propagate it up
//    }
//}
//
//// Helper method to send a GET request and return the response
//private String sendGetRequest(String url) throws IOException {
//    URL obj = new URL(url);
//    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//    con.setRequestMethod("GET");
//
//    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//    String inputLine;
//    StringBuffer response = new StringBuffer();
//
//    while ((inputLine = in.readLine()) != null) {
//        response.append(inputLine);
//    }
//    in.close();
//
//    return response.toString();
    }

    @FXML
    private void newacc(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/Front_Registration.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }

    }

    private void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Verifiez vos données", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void Forget_Password(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("../../../gui/Forget_Password.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }

    }

}
