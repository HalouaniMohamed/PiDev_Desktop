/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.echkili.gui;

import java.io.IOException;
import tn.echkili.entities.User;
import tn.echkili.gui.ModifierUserController;
import tn.echkili.services.UserCRUD;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.echkili.tools.Connexion;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<User> tableviewUser;
       @FXML
    private TableColumn<?, ?> idUser;
    @FXML
    private TableColumn<?, ?> Username;
    @FXML
    private TableColumn<?, ?> NumeroUser;
    @FXML
    private TableColumn<?, ?> EmailUser;
    @FXML
    private TableColumn<?, ?> AdresseUser;
   
  

    
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    User user = null;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void exit(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRec();
    }    
     public  ObservableList<User> getUserList() {
         cnx = Connexion.getInstance().getCnx();
        
        ObservableList<User> UserList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * FROM  user ";
                PreparedStatement smt = cnx.prepareStatement(query2);
                User user;
                ResultSet rs= smt.executeQuery();
            while(rs.next()){
                user=new User(rs.getInt("id"),rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"));
                UserList.add(user);
            }
                System.out.println(UserList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return UserList;
   
    }
   
     public void showRec(){
       
         ObservableList<User> list = getUserList();
         idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
         Username.setCellValueFactory(new PropertyValueFactory<>("UserName"));
         NumeroUser.setCellValueFactory(new PropertyValueFactory<>("numero"));
         EmailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
         AdresseUser.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      
         tableviewUser.setItems(list);
         
     }
     private void refresh(){
       ObservableList<User> list = getUserList();
         idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
         Username.setCellValueFactory(new PropertyValueFactory<>("UserName"));
         NumeroUser.setCellValueFactory(new PropertyValueFactory<>("numero"));
         EmailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
         AdresseUser.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      
         tableviewUser.setItems(list);
       
    }
    @FXML
    private void SupprimerUser(ActionEvent event) {
         UserCRUD u = new UserCRUD();
    User user = tableviewUser.getSelectionModel().getSelectedItem();
    u.supprimerUtilisateur(user);
    refresh();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Travel Me :: Error Message");
    alert.setHeaderText(null);
    alert.setContentText("Utilisateur supprimé");
    alert.showAndWait(); 

    }

    @FXML
    private void ModifierUser(ActionEvent event) {
        user = tableviewUser.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ModifierUser.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                            ModifierUserController muc = loader.getController();
                           // mrc.setUpdate(true);
                            muc.setTextFields(user);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            showRec();
                             
    }
   
    
   
   
}
