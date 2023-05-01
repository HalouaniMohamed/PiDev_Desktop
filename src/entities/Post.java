/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
    private int likes;
    private int dislike;
    
    public Post() {
        commentaires = new ArrayList<>();
    }
      public Post(int id_user, String description, String publication, String nom_utilisateur ) {
        this.id_user = id_user;
        this.description = description;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
        commentaires = new ArrayList<>();
         this.likes = likes;
          
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
    
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public Post(int id_user, String description, String publication, String nom_utilisateur, int likes, int dislike) {
        this.id_user = id_user;
        this.description = description;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
        this.likes = likes;
        this.dislike = dislike;
    }

    public Post( int id_user, String publication, String nom_utilisateur, List<Commentaire> commentaires, int likes, int dislike) {
        
        this.id_user = id_user;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
        this.commentaires = commentaires;
        this.likes = likes;
        this.dislike = dislike;
    }

    public Post(int id_user, String publication, String nom_utilisateur, int likes, int dislike) {
        this.id_user = id_user;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
        this.likes = likes;
        this.dislike = dislike;
    }

   
    
    public Post(int id, int id_user, String description, String publication, String nom_utilisateur, int likes ) {
        this.id = id;
        this.id_user = id_user;
        this.description = description;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
        this.likes = likes;
        this.dislike = dislike;
    }

    
    @Override
    public String toString() {
        return "Post " + id +  ": "  + description  + "\nPubliée par: " + publication + "\nCommentaire :" + commentaires ;
    }
    
}
