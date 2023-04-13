/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.echkili.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import tn.echkili.entities.User;
import tn.echkili.tools.Connexion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rayen
 */
public class UserCRUD {
    
    Connection cnx2;
    public UserCRUD(){
        cnx2 = Connexion.getInstance().getCnx();
    }
   public void supprimerUtilisateur(User user) {
        try {
            String requete="delete from user where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1,user.getId());
            pst.executeUpdate();
           
            System.out.println("Utlisateur est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
   public void modifierUtilisateur(User user) {
        try {
            String requete2="update user set user_name=?,numero=?,email=?,adresse=? where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, user.getUserName());
            pst.setInt(2, user.getNumero());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getAdresse());
            pst.setInt(5, user.getId());
            pst.executeUpdate();
           
            System.out.println("Utlisateur est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    
    
}

