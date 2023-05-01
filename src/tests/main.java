/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.List;
import entities.Commentaire;
import tools.MyConnection;
import entities.Post;
import services.CommentaireService;
import services.PostService;




/**
 *
 * @author asus
 */
public class main { 
     
   public static void main(String[] args) {
    //***********Ajout&Suppression*************//
 
    PostService ps = new PostService();
        
    ps.afficher();
    

    
    System.out.println("////////////////////La liste des commentaires//////////////////////");
   //***********Ajout&Suppression*************//
    CommentaireService cs = new CommentaireService();
    Commentaire c7 = new Commentaire(1000, 1000, "Reponse maryem");
    Commentaire c8 = new Commentaire(2, 1000, "Reponse maryem deux");
     Commentaire c9 = new Commentaire(1, 1000, "Reponse maryem ");
 // cs.ajouter(c8);
   //***********Modification*************//
    Commentaire commentaireModifie = new Commentaire();
    commentaireModifie.setId_user(1000); // Nouvel ID utilisateur
    commentaireModifie.setCommentaires_id(1000); // ID du post à modifier
    commentaireModifie.setReponse("Reponse maryem mod deux"); // Nouvelle reponse
   // Appel de la méthode modifier pour mettre à jour le commentaire dans la base de données
  // cs.modifier(commentaireModifie);
   //************Affichage***************//
//    List<Commentaire> commentaires = cs.afficher();
//    for (Commentaire c : commentaires) {
//        System.out.println(c);
//    }
    
}

    
}
