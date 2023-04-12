/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author asus
 */
public class Article {
    private int id , id_user;
    private String article , nom_utilisateur , image ;

    public Article() {
    }

    public Article(int id_user, String article, String nom_utilisateur, String image) {
        this.id_user = id_user;
        this.article = article;
        this.nom_utilisateur = nom_utilisateur;
        this.image = image;
    }

    public Article(int id, int id_user, String article, String nom_utilisateur, String image) {
        this.id = id;
        this.id_user = id_user;
        this.article = article;
        this.nom_utilisateur = nom_utilisateur;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", id_user=" + id_user + ", article=" + article + ", nom_utilisateur=" + nom_utilisateur + ", image=" + image + '}';
    }
    
    
}
