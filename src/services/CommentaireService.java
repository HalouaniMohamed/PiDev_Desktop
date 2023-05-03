package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Commentaire;
import tools.MyConnection;

public class CommentaireService implements NewInterface<Commentaire> {
    Connection cnx;
    String sql = "";

    public CommentaireService() {
        
        cnx = MyConnection.getInstance().getCnx();
    
    }

    @Override
 
public void ajouter(Commentaire c) {
    
     sql = "insert into commentaire (id_user, commentaires_id, reponse) values (?, ?, ?)";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        
        ste.setInt(1, c.getId_user());
        ste.setInt(2, c.getCommentaires_id());
        ste.setString(3, c.getReponse());

        ste.executeUpdate();
        System.out.println("Commentaire ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
public void modifier(Commentaire c) {
    sql = "UPDATE commentaire SET id_user = ?, commentaires_id = ?,reponse = ?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, c.getId_user());
        ste.setInt(2, c.getCommentaires_id());
        ste.setString(3, c.getReponse());
        
        int resultat = ste.executeUpdate();
        if(resultat > 0) {
            System.out.println("Commentaire modifié avec succès !");
        } else {
            System.out.println("Erreur lors de la modification du commentaire.");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



    @Override
    public List<Commentaire> afficher() {
        List<Commentaire> commentaires = new ArrayList<>();
        sql = "select * from commentaire";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Commentaire p = new Commentaire(
                        rs.getInt("commentaires_id"),
                        rs.getInt("id_user"),
                        rs.getString("reponse")
                );
                p.setId(rs.getInt("id"));
               commentaires.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentaires;
    }
  @Override
    public Commentaire getById(int id) {
        sql = "SELECT * FROM post WHERE id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                Commentaire c = new Commentaire (
                    rs.getInt("id_user"),
                    rs.getInt("commentaires_id"),
                    rs.getString("reponse")
                    
                );
                c.setId(rs.getInt("id"));
                return c;
            } else {
                System.out.println("Le commentaire avec l'ID " + id + " n'existe pas.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
      public void supprimer(Commentaire c) {
        sql="delete from commentaire where id="+c.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      }
      //afficher par id_user
public List<Commentaire> afficherCommentaireParId(int id_user) {
List<Commentaire> commentaires = new ArrayList<>();

        sql = "select * from commentaire  WHERE id_user = " + id_user;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Commentaire p = new Commentaire(
                        rs.getInt("commentaires_id"),
                        rs.getInt("id_user"),
                        rs.getString("reponse")
                );
                p.setId(rs.getInt("id"));
               commentaires.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentaires;

}
}


   
