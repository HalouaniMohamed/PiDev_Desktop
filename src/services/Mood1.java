
package services;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Mood;
import tools.MyConnection;


/**
 *
 * @author LENOVO
 */
public class Mood1 implements NewMoodInterface1<Mood>{
     Statement ste;
    Connection conn = MyConnection.getInstance().getCnx();
    String sql="";




    @Override
    public void ajouterMood(Mood M) {
     
        
//       try {
//
//            
//            String req = "INSERT INTO mood`( user_id`, mood, description, mood_id) VALUES ('"+M.getUser_id()+"', '"+M.getMood()+"', '"+M.getDescription()+"', '"+M.getMood_id()+"')";
//           
//            ste = conn.createStatement();
//             
//            ste.executeUpdate(req);
//           
//            System.out.println("hey");  
//            System.out.println("Mood ajouté!!!");
//        } catch (SQLException ex) {
//            System.out.println("Mood non ajouté");
//    
//}
       sql = "insert into mood (user_id, mood, description, mood_id) values (?,?,?,?)";
        try {
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setInt(1, M.getUser_id());
            ste.setString(2, M.getMood());
            ste.setString(3, M.getDescription());
            ste.setInt(4, M.getMood_id());
            ste.executeUpdate();
            System.out.println("mood Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public List<Mood> afficherMood() {
//              List<Mood> list = new ArrayList<>();
//        try {
//            String req = "Select * from mood";
//            Statement st = conn.createStatement();
//           
//            ResultSet RS= st.executeQuery(req);
//            while(RS.next()){
//             Mood M = new Mood();
//             M.setMood(RS.getString(4));
//             M.setDescription(RS.getString(5));
//             M.setId(RS.getInt(1));
//             M.setUser_id(RS.getInt(2));
//             M.setMood_id(RS.getInt(3));
//             
//             list.add(M);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
        


        
               List<Mood> moods = new ArrayList<>();
        sql="select * from mood";
        try {
            Statement ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next()){
                Mood m = new Mood(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("mood_id"),
                        rs.getString("mood"),
                        rs.getString("description"));
                moods.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return moods;
    }

    @Override
    public void supprimerMood(int id) {
//            try {
//            String req = "DELETE FROM mood WHERE id = " + id;
//            Statement st = conn.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Mood deleted !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
            
            
                	try {
            System.out.println(id);
    	String query = "DELETE FROM mood WHERE id = ?";
        System.out.println(query);

		PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, id);
		statement.executeUpdate();
                ResultSet rs= statement.getGeneratedKeys();
            System.out.println("Mood supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierMood(Mood M) {
//               try {
//
//            
//             String req;
//            req = "UPDATE Mood SET user_id = '"+M.getUser_id()
//                    +"', mood_id = '" + M. getMood_id()
//                    + "', mood = '" + M.getMood()
//                    + "' WHERE produit.`id` = " + M.getId();
//            Statement st = conn.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Mood updated !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
               
               
      sql = "UPDATE mood SET user_id = '" + M.getUser_id()
      + "', mood_id = '" + M.getMood_id()
      + "', mood = '" + M.getMood()
      + "', description = '" + M.getDescription().replace("'", "''")
       + "' WHERE mood.`id` = " + M.getId();

    try {
        Statement ste = conn.createStatement();
        ste.executeUpdate(sql);
        System.out.println("Mood modifié");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    public Mood getById(int id) {
        sql = "SELECT * FROM mood WHERE id = ?";
        try {
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                Mood m = new Mood(
                    rs.getInt("user_id"),
                    rs.getInt("mood_id"),
                    rs.getString("mood"),
                    rs.getString("description")
                );
                m.setId(rs.getInt("id"));
                return m;
            } else {
                System.out.println("Le mood avec l'ID " + id + " n'existe pas.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    
    
}





