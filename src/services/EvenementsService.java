package services;

import entities.Evenements;
import entities.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

public class EvenementsService implements EventInterface<Evenements>{
    Connection cnx;
    String sql="";

    public EvenementsService() {
        cnx=MyConnection.getInstance().getCnx();
    }
//Méthode d'ajout :     
    public void ajouter(Evenements e) {
    String sql = "INSERT INTO evenements (nbr_de_places, nom_evenement, lieu_evenement, description_evenement, type, image, date_evenement, heure, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

try {
    PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    ps.setInt(1, e.getNbr_de_places());
    ps.setString(2, e.getNom_evenement());
    ps.setString(3, e.getLieu_evenement());
    ps.setString(4, e.getDescription_evenement());
    ps.setString(5, e.getType());
    ps.setString(6, e.getImage());
    ps.setDate(7, (Date) e.getDate_evenement());
    ps.setTime(8, e.getHeure());
    ps.executeUpdate();
    
    ResultSet rs = ps.getGeneratedKeys();
    if (rs.next()) {
        e.setId(rs.getInt(1));
    }
    
    System.out.println("Evenement ajouté avec succès.");
} catch (SQLException ex) {
    System.out.println("Erreur lors de l'ajout de l'événement : " + ex.getMessage());
}
     
}

    public Evenements getOneById(int id) throws SQLException {
String sql = "SELECT * FROM evenements WHERE id = ?";
        try (
             PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
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

                return e;
            }
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
        return null;    
        
    }
    
    public int getNbr_place(int id){
    
    String sql = "Select * From reservation Where evenements_id = ? "; 
try (
             PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
           
            ResultSet rs = pstmt.executeQuery();


        List<Reservation> personnes = new ArrayList<Reservation>();
        while (rs.next()) {
            Reservation p = new Reservation(rs.getInt("id"),//or rst.getInt(1)
                    rs.getInt("nombre_de_place_areserver"),
                    rs.getString("email"),
                    rs.getString("evenements_id"));
            personnes.add(p);
        }
        int a=0;
        for(Reservation r : personnes){
        a+= r.getNombre_de_place_areserver();
        
        }
    
        return a;
    } catch (SQLException e) {
System.out.println(e.getMessage());
        }
return 0;
    
    
    }


//Méthode d'affichage :  
    @Override
    public ObservableList<Evenements> afficher() {
    	ObservableList<Evenements> evenements =  FXCollections.observableArrayList();
        sql="select * from Evenements";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery(sql);
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

                evenements.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }
    
    
//Méthode de suppression :  
    public void supprimer(int id) {
    	try {
            System.out.println(id);
    	String query = "DELETE FROM evenements WHERE id = ?";
        System.out.println(query);

		PreparedStatement statement = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, id);
		statement.executeUpdate();
                ResultSet rs= statement.getGeneratedKeys();
            System.out.println("Evenement supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
}
  //Modification : 
    public void modifier(Evenements e) {
   sql = "UPDATE evenements SET nbr_de_places = '" + e.getNbr_de_places()
      + "', nom_evenement = '" + e.getNom_evenement()
      + "', lieu_evenement = '" + e.getLieu_evenement()
      + "', description_evenement = '" + e.getDescription_evenement().replace("'", "''")
      + "', type = '" + e.getType()
      + "', image = '" + e.getImage()
      + "', date_evenement = '" + e.getDate_evenement()
      + "', heure = '" + e.getHeure()
      + "', updated_at = NOW() WHERE id = " + e.getId();

    try {
        Statement ste = cnx.createStatement();
        ste.executeUpdate(sql);
        System.out.println("Evenement modifié");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

 public List<Reservation> getR(int id) throws SQLException {
String sql = "Select * From reservation Where evenements_id = ? "; 
try (
             PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
           
            ResultSet rs = pstmt.executeQuery();


        List<Reservation> personnes = new ArrayList<Reservation>();
        while (rs.next()) {
           

      	        Reservation reservation = new Reservation(
      	            rs.getInt("id"),
      	            rs.getInt("nombre_de_place_areserver"),
      	            rs.getString("email"),
                        new EvenementsService().getOneById(rs.getInt("evenements_id"))
      	           
      	        );
      	     
      	                               

            personnes.add(reservation);
        }
    
        return personnes;
    } catch (SQLException e) {
System.out.println(e.getMessage());
        }
return null;
    }   


}
