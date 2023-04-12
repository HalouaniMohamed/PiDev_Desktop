/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.eshkili.services;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.eshkili.entities.JournalMood;
import tn.eshkili.utils.MaConnexion;
/**
 *
 * @author LENOVO
 */
public class Journal {
      Statement ste;
    Connection conn = MaConnexion.getInstance().getCnx();
    
    /**
     *
     * @param J
     */
    public void ajouterJournal(JournalMood J) {
       try {

            
            String req;
           req = "INSERT INTO journal_mood`( id_user`, moods_id) VALUES ('"+J.getId_user()+"', '"+J.getMoods_id()+"')";
                 
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Journal ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Journal non ajouté");
    
}
}
    
    /**
     *
     * @return
     */
    public List<JournalMood> afficherJournal() {
       List<JournalMood> list = new ArrayList<>();
        try {
            String req = "Select * from journal_mood";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             JournalMood J = new JournalMood();
             J.setId(RS.getInt(1));
             J.setId_user(RS.getInt(2));
             J.setMoods_id(RS.getInt(3));
             
             list.add(J);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void supprimerJournal(int id) {
        try {
            String req = "DELETE FROM journal_mood WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Journal deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierMood(JournalMood J) {
        try {

            
             String req;
            req = "' WHERE produit.`id` = "+ "UPDATE Mood SET id_user = '"+J.getId_user()+"', moods_id = '" + J. getMoods_id() + J.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Journal updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierJournal(JournalMood J) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}






