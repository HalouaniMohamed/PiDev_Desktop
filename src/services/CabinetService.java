/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cabinet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MyConnection;



public class CabinetService {
     Connection cnx ;
    String sql="";
     public CabinetService() {
        
        cnx=MyConnection.getInstance().getCnx();
    }
    public void ajouter(Cabinet cabinet) {
    String sql = "INSERT INTO cabinet(nom, adresse) VALUES (?, ?)";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, cabinet.getNom());
        ste.setString(2, cabinet.getAdresse());
        ste.executeUpdate();
        System.out.println("Cabinet ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void modifier(int id, Cabinet cabinet) {
    String sql = "UPDATE cabinet SET nom=?, adresse=? WHERE id=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, cabinet.getNom());
        ste.setString(2, cabinet.getAdresse());
        ste.setInt(3, id);
        ste.executeUpdate();
        System.out.println("Cabinet modifié avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public Cabinet getCabinetById(int id) {
    Cabinet cabinet = null;
    String sql = "SELECT * FROM cabinet WHERE id=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            cabinet = new Cabinet();
            cabinet.setId(rs.getInt("id"));
            cabinet.setNom(rs.getString("nom"));
            cabinet.setAdresse(rs.getString("adresse"));
        }
        rs.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return cabinet;
}

public void supprimer(int id) {
    String sql = "DELETE FROM cabinet WHERE id=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ste.executeUpdate();
        System.out.println("Cabinet supprimé avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


public List<Cabinet> getAllCabinets() {
    List<Cabinet> cabinets = new ArrayList<>();
    String sql = "SELECT * FROM cabinet";
    try {
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Cabinet cabinet = new Cabinet();
            cabinet.setId(rs.getInt("id"));
            cabinet.setNom(rs.getString("nom"));
            cabinet.setAdresse(rs.getString("adresse"));
            cabinets.add(cabinet);
        }
        rs.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return cabinets;
}

 public Cabinet recupererBynom(String nom) throws SQLException {
        String req = "select * from cabinet where nom = ?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, nom);
        ResultSet rs = st.executeQuery();
        Cabinet p = new Cabinet();
        rs.next();
        p.setId(rs.getInt("id"));
        p.setNom(rs.getString("nom"));
        p.setAdresse(rs.getString("adresse"));

        return p;
    }
 public int getCabinetCountByCategory(int CabinetId) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM rendez_vous "
                + "WHERE cabinet_id IN (SELECT id FROM cabinet WHERE cabinet_id = ?)";
        PreparedStatement st = cnx.prepareStatement(query);
        st.setInt(1, CabinetId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        } else {
            throw new SQLException("Erro");
        }

        return count;
    }

    
}
