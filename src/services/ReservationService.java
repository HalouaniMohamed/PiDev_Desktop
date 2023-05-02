/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenements;
import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author ASUS
 */

    public class ReservationService implements EventInterface<Reservation>{
    Connection cnx;
    String sql="";

    public ReservationService() {
        cnx=MyConnection.getInstance().getCnx();
    }
//Ajout d'une réservation : 
    public void ajouter(Reservation r ) {
        sql = "insert into reservation(nombre_de_place_areserver,email , evenements_id) values (?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            System.out.println(r.getE().getId());
           
            System.out.println(r.getEmail());
        
            
            ste.setInt(1, r.getNombre_de_place_areserver());
            ste.setString(2, r.getEmail());
            ste.setInt(3, r.getE().getId());
            ste.executeUpdate();
            
            System.out.println("reservation ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
  //Affichage d'une réservation :   
 public Evenements getEvent(int id){
 String req = "Select evenements_id from reservation where id = ?";
 try{
   PreparedStatement ps = cnx.prepareStatement(req);
   ps.setInt(1, id);
    	    ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
 Evenements e = new EvenementsService().getOneById(rs.getInt("evenements_id"));
                return e;
            }
 }catch(SQLException e){
 
 
 
 
 }
 
 return null;
 
 
 }
 public Reservation getOneById(int id){
 String req = "Select * from reservation where id = ?";
 try{
   PreparedStatement ps = cnx.prepareStatement(req);
   ps.setInt(1, id);
    	    ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
  Reservation reservation = new Reservation(
    	            rs.getInt("id"),
    	            rs.getInt("nombre_de_place_areserver"),
    	            rs.getString("email"),
    	            rs.getString("evenements_id")
    	        );
  return reservation;
            }
             
 }catch(SQLException e){
 
 
 
 
 }
 
 return null;
 
 
 }
  @Override
public ObservableList<Reservation> afficher() {
    	ObservableList<Reservation> reservations =  FXCollections.observableArrayList();
    	String req = "SELECT r.*, e.nom_evenement AS nom FROM reservation r JOIN evenements e ON r.evenements_id = e.id";
    	try {
    	    PreparedStatement ps = cnx.prepareStatement(req);
    	    ResultSet rs = ps.executeQuery();
    	    while (rs.next()) {
    	        Evenements e = new Evenements(
    	            rs.getInt("id"),
    	            rs.getInt("nombre_de_place_areserver"),
    	            rs.getString("e.nom")
    	       
    	        );

    	        Reservation reservation = new Reservation(
    	            rs.getInt("id"),
    	            rs.getInt("nombre_de_place_areserver"),
    	            rs.getString("email"),
    	            rs.getString("nom")
    	        );
    	     
    	        reservation.setE(e);                            

            reservations.add(reservation);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return reservations;
}

//Suppression d'une réservation : 
 public void supprimer(int id) {
    sql = "DELETE FROM reservation WHERE id = " + id;
    try {
        Statement ste = cnx.createStatement();
         ste.executeUpdate(sql);
            System.out.println("Reservation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
}
 // Mise à jour d'une réservation :
    public void modifier(Reservation r) {
        sql = "update reservation set nombre_de_place_areserver=?, email=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, r.getNombre_de_place_areserver());
            ste.setString(2, r.getEmail());
            ste.setInt(3, r.getId());
            ste.executeUpdate();
            System.out.println("Reservation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
     public ObservableList<Reservation> afficherParEmail(String email) {
    	ObservableList<Reservation> reservations =  FXCollections.observableArrayList();
        System.out.println(email);
        String req = "SELECT r.*, e.nom_evenement AS nom FROM reservation r JOIN evenements e ON r.evenements_id = e.id WHERE r.email = ?";
        try {
        	PreparedStatement ps = cnx.prepareStatement(req);
        	ps.setString(1, email); // Set the value of the parameter
        	ResultSet rs = ps.executeQuery();
      	    while (rs.next()) {
      	        Evenements e = new Evenements(
      	            rs.getInt("id"),
      	            rs.getInt("nombre_de_place_areserver"),
      	            rs.getString("nom")
      	       
      	        );

      	        Reservation reservation = new Reservation(
      	            rs.getInt("id"),
      	            rs.getInt("nombre_de_place_areserver"),
      	            rs.getString("email"),
      	            e
      	        );
      	     
      	        reservation.setE(e);                            

              reservations.add(reservation);
      	    }
            // V�rifier s'il y a des r�servations dans la liste
            if (reservations.size() == 0) {
                System.out.println("Aucune reservation trouvee pour l'email " + email);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reservations;
    }



}
