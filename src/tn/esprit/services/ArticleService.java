/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Article;
import tn.esprit.tools.ConnexionBD;
/**
 *
 * @author asus
 */
public class ArticleService implements NewInterface<Article>{
    Connection cnx;
    String sql="";
  public  ArticleService() {
        cnx=ConnexionBD.getInstance().getCnx();
    }
     @Override
    public Article getById(int id) {
        sql = "SELECT * FROM post WHERE id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                Article a = new Article (
                    rs.getInt("id_user"),
                    rs.getString("article"),
                    rs.getString("nom_utilisateur"),
                    rs.getString("image")
                    
                );
                a.setId(rs.getInt("id"));
                return a;
            } else {
                System.out.println("L'article avec l'ID " + id + " n'existe pas.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public void ajouter(Article a) {
        sql = "insert into article (id,id_user,nom_utilisateur,Article,Image) values (?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, a.getId());
            ste.setInt(2, a.getId_user());
            ste.setString(3, a.getNom_utilisateur());
            ste.setString(4, a.getArticle());
            ste.setString(5, a.getImage());
            ste.executeUpdate();
            System.out.println("Article Ajouté!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public void modifier(Article a) {
    sql = "UPDATE post SET id_user = ?, nom_utilisateur = ?, Article = ?, Image = ? WHERE id = ?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, a.getId_user());
        ste.setString(2, a.getNom_utilisateur());
        ste.setString(3, a.getArticle());
        ste.setString(4, a.getImage());
        ste.setInt(5, a.getId());
        int resultat = ste.executeUpdate();
        if(resultat > 0) {
            System.out.println("Artcile modifié avec succès !");
        } else {
            System.out.println("Erreur lors de la modification du l'article.");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    @Override
    public List<Article> afficher() {
        List<Article> articles = new ArrayList<>();
        sql="select * from article";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next()){
                Article a = new Article(
                        rs.getInt("id_user"),
                        rs.getString("article"),
                        rs.getString("Image"),
                        rs.getString("nom_utilisateur")
                );
                a.setId(rs.getInt("id"));
                articles.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return articles;
    }


   
    public void supprimer(Article a) {
        sql="delete from article where id="+a.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Artcile supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
    

