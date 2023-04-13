/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Commentaire {
    private int id  , id_user , commentaires_id ;
    private String reponse ;
    private Post post ;

    public Commentaire() {
         
    }
   
   

    public Commentaire(int id, int commentaires_id, int id_user, String reponse, Post post) {
    this.id = id;
    this.commentaires_id = commentaires_id;
    this.id_user = id_user;
    this.reponse = reponse;
    this.post = post;
}


    public int getCommentaires_id() {
        return commentaires_id ;
    }

    public void setCommentaires_id(int commentaires_id) {
        this.commentaires_id = commentaires_id;
    }

    public Commentaire( int id_user,int commentaires_id, String reponse) {
       
        this.id_user = id_user;
          this.commentaires_id =  commentaires_id;
        this.reponse = reponse;
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

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "Réponse:" + reponse + ", du post numéro:" + id_user + '}';
    }
    

   
 
    
}
