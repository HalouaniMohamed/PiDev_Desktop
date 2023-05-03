/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static jdk.nashorn.internal.runtime.Debug.id;
import entities.Commentaire;
import entities.Post;
import tools.MyConnection;
/**
 *
 * @author asus
 */
public class PostService implements NewInterface<Post>{
    Connection cnx;
    String sql="";
  public PostService() {
        cnx=MyConnection.getInstance().getCnx();
    }
    

    @Override
    public void ajouter(Post p) {
        sql = "insert into post (id,id_user,nom_utilisateur,description,publication) values (?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.setInt(2, p.getId_user());
            ste.setString(3, p.getNom_utilisateur());
            ste.setString(4, p.getDescription());
            ste.setString(5, p.getPublication());
            ste.executeUpdate();
            System.out.println("Post Ajouté!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    
    public void modifier(Post p) {
    sql = "UPDATE post SET id_user = ?, nom_utilisateur = ?, description = ?, publication = ? WHERE id = ?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, p.getId_user());
        ste.setString(2, p.getNom_utilisateur());
        ste.setString(3, p.getDescription());
        ste.setString(4, p.getPublication());
        ste.setInt(5, p.getId());
        int resultat = ste.executeUpdate();
        if(resultat > 0) {
            System.out.println("Post modifié avec succès !");
        } else {
            System.out.println("Erreur lors de la modification du post.");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public List<Post> afficher() {
    List<Post> posts = new ArrayList<>();
    sql = "SELECT p.*, c.id AS commentaires_id, c.id_user AS commentaires_id_user, c.reponse AS commentaire_reponse " +
            "FROM post p LEFT JOIN commentaire c ON p.id = c.commentaires_id";
    try {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            Post p = new Post(
                    rs.getInt("id_user"),
                    rs.getString("nom_utilisateur"),
                    rs.getString("description"),
                    rs.getString("publication")
                    
                  //  rs.getInt("likes"),
                   // rs.getInt("dislike")
                     
                    
                   
                    
            );
            p.setId(rs.getInt("id"));

            // Ajouter le commentaire associé au post à sa liste de commentaires
            if (rs.getInt("commentaires_id") != 0) {
                Commentaire c = new Commentaire(
                        rs.getInt("commentaires_id_user"),
                        rs.getInt("commentaires_id"),
                        rs.getString("commentaire_reponse")
                );
                p.ajouterCommentaire(c);
            }

            // Vérifier si le post a déjà été ajouté à la liste de posts
            boolean postExists = false;
            for (Post post : posts) {
                if (post.getId() == p.getId()) {
                    // Ajouter le commentaire associé au post à sa liste de commentaires
                    if (rs.getInt("commentaires_id") != 0) {
                        Commentaire c = new Commentaire(
                                rs.getInt("commentaires_id_user"),
                                rs.getInt("commentaires_id"),
                                rs.getString("commentaire_reponse")
                        );
                        post.ajouterCommentaire(c);
                    }
                    postExists = true;
                    break;
                }
            }
            // Si le post n'a pas encore été ajouté, ajouter le post à la liste de posts
            if (!postExists) {
                posts.add(p);
            }
        }
        for (Post pc : posts) {
            System.out.println("Post :");
            System.out.println(pc);
            System.out.println("Commentaires :");
            pc.afficherCommentaires();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return posts;
}


   @Override
    public Post getById(int id) {
        sql = "SELECT * FROM post WHERE id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                Post p = new Post(
                    rs.getInt("id_user"),
                    rs.getString("description"),
                    rs.getString("publication"),
                    rs.getString("nom_utilisateur")
                );
                p.setId(rs.getInt("id"));
                return p;
            } else {
                System.out.println("Le post avec l'ID " + id + " n'existe pas.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }


    @Override
public void supprimer(Post p) {
    try {
        // Supprimer les commentaires liés à ce post
        String sqlDeleteComments = "DELETE FROM commentaire WHERE commentaires_id = ?";
        PreparedStatement psDeleteComments = cnx.prepareStatement(sqlDeleteComments);
        psDeleteComments.setInt(1, p.getId());
        int rowsDeletedComments = psDeleteComments.executeUpdate();
        System.out.println(rowsDeletedComments + " commentaires ont été supprimés pour le post avec l'ID " + p.getId());
        
        // Supprimer le post lui-même
        String sqlDeletePost = "DELETE FROM post WHERE id = ?";
        PreparedStatement psDeletePost = cnx.prepareStatement(sqlDeletePost);
        psDeletePost.setInt(1,  p.getId());
        int rowsDeletedPost = psDeletePost.executeUpdate();
        if (rowsDeletedPost > 0) {
            System.out.println("Post avec l'ID " + p.getId() + " a été supprimé.");
        } else {
            System.out.println("Le post avec l'ID " + p.getId() + " n'existe pas.");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    public void setlike(Post p) {
        
        try {
        String req = "SELECT likes FROM post WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        int id = p.getId();
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int likes = rs.getInt("likes");
            likes++;
            req = "UPDATE post SET likes = ? WHERE id = ?";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, likes);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    } catch (SQLException ex) {
        Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    }

    public void setdislike(Post p) {
         try {
        String req = "SELECT dislike FROM post WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        int id = p.getId();
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int dislike = rs.getInt("dislike");
            dislike++;
            req = "UPDATE post SET dislike = ? WHERE id = ?";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, dislike);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    } catch (SQLException ex) {
        Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }
         public void incrementLike(Post post) {
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("UPDATE post SET Like = islike + 1 WHERE id_annonce = ?");
            preparedStatement.setInt(1, post.getId()); // remplacer "id" par l'attribut qui représente l'ID de l'annonce
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    
 

  

    
}
    

