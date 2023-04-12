/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.pidev.entites.Evenements;
import tn.pidev.entites.Reservation;
import tn.pidev.tools.ConnexionBD;

/**
 *
 * @author ASUS
 */

    public class ReservationService implements NewInterface<Reservation>{
    Connection cnx;
    String sql="";

    public ReservationService() {
        cnx=ConnexionBD.getInstance().getCnx();
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
    @Override
public List<Reservation> afficher() {
    List<Reservation> reservations = new ArrayList<>();
    
    String req = "SELECT r.*, e.* FROM reservation r JOIN evenements e ON r.evenements_id = e.id";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            
                Evenements e;
                e = new Evenements(rs.getInt("id"),
                        rs.getInt("nbr_de_places"),
                        rs.getString("nom_evenement"),
                        rs.getString("lieu_evenement"),
                        rs.getString("description_evenement"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getDate("date_evenement"),
                        rs.getTime("heure"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());

            Reservation reservation = new Reservation(rs.getInt("id"),
                                                       rs.getInt("nombre_de_place_areserver"),
                                                       rs.getString("email"),
                                                      e);

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

}
