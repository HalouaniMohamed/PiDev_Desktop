/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.JournalMood;
import tools.MaConnexion;
/**
 *
 * @author LENOVO
 */
public class Journal implements InterfaceJournal<JournalMood> {
      Statement ste;
    Connection conn = MaConnexion.getInstance().getCnx();
    String sql="";
    
    /**
     *
     * @param J
     */

   
      @Override
    public void ajouterJournal(JournalMood J) {
//       try {
//
//            
//            String req;
//           req = "INSERT INTO journal_mood`( id_user, moods_id) VALUES ('"+J.getId_user()+"', '"+J.getMoods_id()+"')";
//                 
//            ste = conn.createStatement();
//            ste.executeUpdate(req);
//            System.out.println("Journal ajouté!!!");
//        } catch (SQLException ex) {
//            System.out.println("Journal non ajouté");
//    
//}



   
       try {
            String req = "INSERT INTO `journal_mood`( id_user, moods_id) VALUES ('"+J.getId_user()+"', '"+J.getMoods_id()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Journal ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Journnal non ajouté");
    
    
}
        
// /*      sql = "insert into journal_mood (id_user, moods_id) values (?,?)";
//        try {
//            PreparedStatement ste = conn.prepareStatement(sql);
//            ste.setInt(1, J.getId_user());
//            ste.setInt(2, J.getMoods_id());
//
//
//            ste.executeUpdate();
//            System.out.println("Journal Ajouté !!");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }*/
}
    
    /**
     *
     * @return
     */
      @Override
    public List<JournalMood> afficherJournal() {
        
//       List<JournalMood> list = new ArrayList<>();
//        try {
//            String req = "Select * from journal_mood";
//            Statement st = conn.createStatement();
//           
//            ResultSet RS= st.executeQuery(req);
//            while(RS.next()){
//             JournalMood J = new JournalMood();
//             J.setId(RS.getInt(1));
//             J.setId_user(RS.getInt(2));
//             J.setMoods_id(RS.getInt(3));
//             
//             list.add(J);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
        
                
               List<JournalMood> journals = new ArrayList<>();
        sql="select * from journal_mood";
        try {
            Statement ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next()){
                JournalMood j = new JournalMood(
                        rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getInt("moods_id"));

                journals.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return journals;
    }

      @Override
    public void supprimerJournal(int id) {
        
        
        
                	try {
            System.out.println(id);
    	String query = "DELETE FROM journal_mood WHERE id = ?";
        System.out.println(query);

		PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, id);
		statement.executeUpdate();
                ResultSet rs= statement.getGeneratedKeys();
            System.out.println("Journal supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                        
                        
//        try {
//            String req = "DELETE FROM journal_mood WHERE id = " + id;
//            Statement st = conn.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Journal deleted !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
    
    /**
     *
     * @param J
     */
      @Override
    public void modifierJournal(JournalMood J) {
        
        
                       
      sql = "UPDATE journal_mood SET id_user = '" + J.getId_user()
      + "', moods_id = '" + J.getMoods_id()
       + "' WHERE journal_mood.`id` = " + J.getId();

    try {
        Statement ste = conn.createStatement();
        ste.executeUpdate(sql);
        System.out.println("Journal modifié");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
//    
//        try {
//
//            
//             String req;
//            req = "' WHERE produit.`id` = "+ "UPDATE Mood SET id_user = '"+J.getId_user()+"', moods_id = '" + J. getMoods_id() + J.getId();
//            Statement st = conn.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Journal updated !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

 
    
}






