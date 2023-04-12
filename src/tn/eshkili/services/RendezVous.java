/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.eshkili.entities.Cabinet;
import tn.eshkili.entities.Medecin;
import tn.eshkili.entities.rendez_vous;
import tn.eshkili.utils.MaConnexion;

/**
 *
 * @author Mongi
 */
public class RendezVous{
    
    Connection cnx ;
    String sql="";

    public RendezVous() {
        
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    
    
    
    
    

//   
//public void ajouter(rendez_vous p) {
//    sql = "INSERT INTO rendez_vous(nom, prenom, cause, description, date_rv) VALUES (?, ?, ?, ?, ?)";
//    try {
//        PreparedStatement ste = cnx.prepareStatement(sql);
//        ste.setString(1, p.getNom());
//        ste.setString(2, p.getPrenom());
//        ste.setString(3, p.getCause());
//        ste.setString(4, p.getDescription());
//        ste.setDate(5, new java.sql.Date(p.getDate_rv().getTime()));
//        ste.executeUpdate();
//        System.out.println("Rendez-vous ajouté avec succès !");
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//}


    

   

   


    
//public void modifier(int id, rendez_vous p) {
//    sql = "UPDATE rendez_vous SET nom=?, prenom=?, cause=?, description=?, date_rv=? WHERE id=?";
//    try {
//        PreparedStatement ste = cnx.prepareStatement(sql);
//        ste.setString(1, p.getNom());
//        ste.setString(2, p.getPrenom());
//        ste.setString(3, p.getCause());
//        ste.setString(4, p.getDescription());
//        ste.setDate(5, new java.sql.Date(p.getDate_rv().getTime()));
//        ste.setInt(6, id);
//        ste.executeUpdate();
//        System.out.println("Rendez-vous modifié avec succès !");
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//}
//



   
    public rendez_vous getRendezVousById(int id) {
    rendez_vous rv = null;
    String sql = "SELECT * FROM rendez_vous WHERE id=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            rv = new rendez_vous();
            rv.setId(rs.getInt("id"));
            rv.setNom(rs.getString("nom"));
            rv.setPrenom(rs.getString("prenom"));
            rv.setCause(rs.getString("cause"));
            rv.setDescription(rs.getString("description"));
            rv.setDate_rv(rs.getDate("date_rv"));
        }
        rs.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return rv;
}
    
    
    
    
    
    
public void supprimer(int id) {
    String sql = "DELETE FROM rendez_vous WHERE id=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ste.executeUpdate();
        System.out.println("Rendez-vous supprimé avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}








//public List<rendez_vous> afficher() {
//    List<rendez_vous> listRv = new ArrayList<>();
//    String sql = "SELECT * FROM rendez_vous";
//    try {
//        Statement ste = cnx.createStatement();
//        ResultSet rs = ste.executeQuery(sql);
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String nom = rs.getString("nom");
//            String prenom = rs.getString("prenom");
//            String cause = rs.getString("cause");
//            String description = rs.getString("description");
//            Date date_rv = rs.getDate("date_rv");
//            rendez_vous rv = new rendez_vous(id, nom, prenom, cause, description, date_rv);
//            listRv.add(rv);
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//    return listRv;
//}
//





//public void ajouterRendezVousChezMédecin(rendez_vous rv, Medecin medecin) {
//    sql = "INSERT INTO rendez_vous(nom, prenom, cause, description, date_rv, medecin_id) VALUES (?, ?, ?, ?, ?, ?)";
//    try {
//        PreparedStatement ste = cnx.prepareStatement(sql);
//        ste.setString(1, rv.getNom());
//        ste.setString(2, rv.getPrenom());
//        ste.setString(3, rv.getCause());
//        ste.setString(4, rv.getDescription());
//        ste.setDate(5, new java.sql.Date(rv.getDate_rv().getTime()));
//        ste.setInt(6, medecin.getId());
//        ste.executeUpdate();
//        System.out.println("Rendez-vous chez le médecin ajouté avec succès !");
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//}












public void add(rendez_vous rv, Medecin medecin, Cabinet cabinet) {
    sql = "INSERT INTO rendez_vous(nom, prenom, cause, description, date_rv, medecin_id, cabinet_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, rv.getNom());
        ste.setString(2, rv.getPrenom());
        ste.setString(3, rv.getCause());
        ste.setString(4, rv.getDescription());
        ste.setDate(5, new java.sql.Date(rv.getDate_rv().getTime()));
        ste.setInt(6, medecin.getId());
        ste.setInt(7, cabinet.getId());
        ste.executeUpdate();
        System.out.println("Rendez-vous est ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    
}









public void modifierRendezVous(int id, rendez_vous rv, Medecin medecin, Cabinet cabinet) {
    sql = "UPDATE rendez_vous SET nom=?, prenom=?, cause=?, description=?, date_rv=?, medecin_id=?, cabinet_id=? WHERE id=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, rv.getNom());
        ste.setString(2, rv.getPrenom());
        ste.setString(3, rv.getCause());
        ste.setString(4, rv.getDescription());
        ste.setDate(5, new java.sql.Date(rv.getDate_rv().getTime()));
        ste.setInt(6, medecin.getId());
        ste.setInt(7, cabinet.getId());
        ste.setInt(8, id);
        ste.executeUpdate();
        System.out.println("Rendez-vous avec l'identifiant " + id + " modifié avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void afficherRendezVous() {
    try {
        String sql = "SELECT * FROM rendez_vous INNER JOIN medecin ON rendez_vous.medecin_id = medecin.id INNER JOIN cabinet ON rendez_vous.cabinet_id = cabinet.id ORDER BY date_rv ASC";
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        System.out.println("Liste des rendez-vous :");
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String cause = rs.getString("cause");
            String description = rs.getString("description");
            Date date_rv = rs.getDate("date_rv");
            String medecin_nom = rs.getString("medecin.nom");
            String medecin_prenom = rs.getString("medecin.prenom");
            String cabinet_nom = rs.getString("cabinet.nom");
            
            System.out.println("Nom : " + nom);
            System.out.println("Prénom : " + prenom);
            System.out.println("Cause : " + cause);
            System.out.println("Description : " + description);
            System.out.println("Date : " + date_rv);
            System.out.println("Médecin : " + medecin_nom + " " + medecin_prenom);
            System.out.println("Cabinet : " + cabinet_nom);
            System.out.println("-------------------------------");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}






   public Medecin getMedecinById(int id) {
    sql = "SELECT * FROM medecin WHERE id = ?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            Medecin medecin = new Medecin();
            medecin.setId(rs.getInt("id"));
            medecin.setNom(rs.getString("nom"));
            medecin.setPrenom(rs.getString("prenom"));
            medecin.setNum_telephone(rs.getInt("num_telephone"));
            medecin.setAdresse(rs.getString("adresse"));
            return medecin;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}

   
   
   public Cabinet getCabinetById(int id) {
    sql = "SELECT * FROM cabinet WHERE id=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            return new Cabinet(rs.getInt("id"), rs.getString("adresse"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}


    
}
