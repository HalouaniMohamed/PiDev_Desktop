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
import tn.eshkili.entities.Mood;
import tn.eshkili.utils.MaConnexion;


/**
 *
 * @author LENOVO
 */
public abstract class Mood {
     Statement ste;
    Connection conn = MaConnexion.getInstance().getCnx();
    
    public void ajouterMood(Mood M) {
       try {

            
            String req = "INSERT INTO mood`( user_id`, mood, description, mood_id) VALUES ('"+M.getUser_id()+"', '"+M.getMood()+"', '"+M.getDescription()+"', '"+M.getMood_id()+"')";
                 
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Mood ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Mood non ajouté");
    
}
}
    
    public List<Mood> afficherMood() {
       List<Mood> list = new ArrayList<>();
        try {
            String req = "Select * from mood";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Mood M = new Mood();
             M.setMood(RS.getString(4));
             M.setDescription(RS.getString(5));
             M.setId(RS.getInt(1));
             M.setUser_id(RS.getInt(2));
             M.setMood_id(RS.getInt(3));
             
             list.add(M);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public void supprimerMood(int id) {
        try {
            String req = "DELETE FROM mood WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Mood deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierMood(Mood M) {
        try {

            
             String req;
            req = "UPDATE Mood SET user_id = '"+M.getUser_id()+"', mood_id = '" + M. getMood_id()+ "', mood = '" + M.getMood()+ "' WHERE produit.`id` = " + M.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Mood updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}





