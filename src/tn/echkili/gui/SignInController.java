/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.echkili.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.echkili.entities.User;
import tn.echkili.services.UserCRUD;
import tn.echkili.tools.Connexion;
import tn.echkili.tools.SessionManager;


/**
 * FXML Controller class
 *
 * @author rayen
 */
public class SignInController implements Initializable {

    @FXML
    private Label echkili;
    @FXML
    private TextField email_signin;
    @FXML
    private PasswordField password_signin;
    @FXML
    private Button login_btn;
    @FXML
    private Hyperlink create_acc;
    @FXML
    private Label echkili1;
    @FXML
    private TextField username;
    @FXML
    private Button signup_btn;
    @FXML
    private Hyperlink login_acc;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private AnchorPane login_form;
    @FXML
    private PasswordField confirm_password;
    @FXML
    private TextField numero;
    @FXML
    private PasswordField password_signup;
    
    @FXML
    private TextField adresse;
    @FXML
    private TextField email_signup;
    @FXML
    private Hyperlink mdp_oub;

    /**
     * Initializes the controller class.
     */
    
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
     public void exit(){
        System.exit(0);
    }   
   
    public void changeForm(ActionEvent event){
        if(event.getSource() == create_acc){
            signup_form.setVisible(true);
             login_form.setVisible(false);
        }else if(event.getSource()==login_acc){
            login_form.setVisible(true);
            signup_form.setVisible(false);
        }
    }
    
    public boolean ValidationEmail(){ 
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(email_signup.getText());
        
        if(match.find() && match.group().equals(email_signup.getText()))
        {
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();
            
            return false;
        }
    }
    
    public void login(ActionEvent event) throws IOException{
        if(email_signin.getText().equals("rayen.baccouch@esprit.tn") && password_signin.getText().equals("adminadmin") )
        {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("ECHKILI :: Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Bienvenu Admin");
                     alert.showAndWait();
                     
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else {
            
            String query2="select * from user where email=?  and password=?";
            cnx = Connexion.getInstance().getCnx();
           try{
              PreparedStatement smt = cnx.prepareStatement(query2);
       
               smt.setString(1,email_signin.getText());
               smt.setString(2,password_signin.getText());
               ResultSet rs= smt.executeQuery();
               User p;
                if(rs.next()){
                     p=new User(rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("password"));
                     User.setCurrent_User(p);
                     SessionManager.getInstace(rs.getInt("id"),rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
                     System.out.println(User.Current_User.getEmail());
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("ECHKILI" );
                     alert.setHeaderText(null);
                     alert.setContentText("Vous etes connecté");
                     alert.showAndWait();
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
                   
                    
                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ECHKILI");
                alert.setHeaderText(null);
                alert.setContentText("Email/Password invalide !!");
                alert.showAndWait();  
                }
          
      }catch(Exception ex){
           System.out.println(ex.getMessage());
      }

            
        }
         
    }
     @FXML
    public void  signUp(){
      
    cnx = Connexion.getInstance().getCnx();
    String query="INSERT INTO user (user_name, numero, email, adresse, password,roles)"
                    + "VALUES (?, ?, ?, ?, ?,'ROLE_USER')";
    
    try{
        
          if( username.getText().isEmpty()
                    | numero.getText().isEmpty()
                    | email_signup.getText().isEmpty()
                    | password_signup.getText().isEmpty()
                    | adresse.getText().isEmpty()
                    | confirm_password.getText().isEmpty()){
              
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ECHKILI");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir les champs !!");
                alert.showAndWait();
    
        
         
          }else if(confirm_password.getText().length() < 8 | confirm_password.getText()==password_signup.getText() ){
              
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ECHKILI ");
                alert.setHeaderText(null);
                alert.setContentText("Password doit etre sup 8 caractéres !!");
                alert.showAndWait();
                
          }
          else{
               if(ValidationEmail()){
             PreparedStatement smt = cnx.prepareStatement(query);
        
            smt.setString(1, username.getText());
            smt.setString(2, numero.getText());
            smt.setString(3, email_signup.getText());
            smt.setString(4, adresse.getText());
            smt.setString(5, password_signup.getText());
            smt.executeUpdate();
             
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ECHKILI :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
                
                login_form.setVisible(true);
                signup_form.setVisible(false);
                
          }
        
            
          }}catch(SQLException ex){
         System.out.println(ex.getMessage());
    }
 }
  
    
     

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //dropShadowEffect();
    }    
    
}
