/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class Post {
    private int id, id_user;
    private String description, publication, nom_utilisateur;
    private List<Commentaire> commentaires;
    
    public Post() {
        commentaires = new ArrayList<>();
    }
      public Post(int id_user, String description, String publication, String nom_utilisateur) {
        this.id_user = id_user;
        this.description = description;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
        commentaires = new ArrayList<>();
    }
        public List<Commentaire> getCommentaires() {
        return commentaires;
    }
          public void ajouterCommentaire(Commentaire commentaire) {
        commentaires.add(commentaire);
    }
          public void afficherCommentaires() {
    for (Commentaire c : commentaires) {
        System.out.println(c.getReponse());
    }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }
    
    public Post(int id, int id_user, String description, String publication, String nom_utilisateur) {
        this.id = id;
        this.id_user = id_user;
        this.description = description;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
    }

    
    @Override
    public String toString() {
        return "Post{" + "numéro:" + id +  ", publication: "  + nom_utilisateur+ ", publiée par:" + description + ", Description:" + publication + ", Commentaire :" + commentaires +'}';
    }
    
}
