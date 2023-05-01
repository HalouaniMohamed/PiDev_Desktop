/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Evenements;
import entities.Reservation;
 import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import services.EvenementsService;
import services.ReservationService;
import tools.MyConnection;


/**
 *
 * @author ASUS
 */
public class main {
    public static void main (String[] args ) {
        MyConnection cb = MyConnection.getInstance();
        //ConnexionBD cb1 = ConnexionBD.getInstance();
        
//Reservation :        
      
      ReservationService rs = new ReservationService();
      //création de nouvelle reservation pour tester l'ajout et la suppression  : 
              Reservation r = new Reservation(2,"xxx");
              
              
              //Reservation r1 = new Reservation(5,"xxxxx");
              Reservation r2 = new Reservation(15,5,"boucha");
               Reservation r5 = new Reservation(5,"boucha");
             // Reservation r23 = new Reservation(20,5,"lll");
//les méthodes ajout, affichage et suppression  :      
      //rs.ajouter(r5);
      //rs.ajouter(r);
     //rs.supprimer(14);
     //System.out.println(rs.afficher());
     
//méthode de test de la modification : 
//     List<Reservation> reservations = rs.afficher();
//     Reservation reservation = reservations.get(0);
//    System.out.println("Reservation avant modification : " + reservation);
//
//    reservation.setNombre_de_place_areserver(5);
//    rs.modifier(reservation);
//
//    reservations = rs.afficher();
//    reservation = reservations.get(0);
//    System.out.println("Reservation après modification : " + reservation);
      
      
      
 //Eevenements : 
EvenementsService es = new EvenementsService();
//méthode ajout:
  Evenements e1 = new Evenements(0, 100, "boucha", "Salle de concert", "Concert de rock", "Musique", "image.png", Date.valueOf("2023-05-01"), Time.valueOf("20:00:00"), LocalDateTime.now(), LocalDateTime.now());
  //es.ajouter(e1);
 //Methode suppression : 
 //es.supprimer(15);
              
 //Méthode affichage :      
      //System.out.println(es.afficher());
      
//Ajout reservation pour e10 : 
//Evenements e11 = new Evenements(100, "bouchaaa", "Salle de concert", "Concert de rock", "Musique", "image.png", Date.valueOf("2023-05-01"), Time.valueOf("20:00:00"), LocalDateTime.now(), LocalDateTime.now());
  //es.ajouter(e11);
// Evenements e12 = new Evenements(24,100, "bouchaaa", "Salle de concert", "Concert de rock", "Musique", "image.png", Date.valueOf("2023-05-01"), Time.valueOf("20:00:00"), LocalDateTime.now(), LocalDateTime.now());

  //es.ajouter(e12);
//Reservation r6 = new Reservation(7,"boucha",e12);
        //System.out.println(r6);
//rs.ajouter(r6);
//
// Evenements e14 = new Evenements(25,100, "bouchaaa", "Salle de concert", "Concert de rock", "Musique", "image.png", Date.valueOf("2023-05-01"), Time.valueOf("20:00:00"), LocalDateTime.now(), LocalDateTime.now());
// //es.ajouter(e12);
// Reservation r7 = new Reservation(7,"boucha",e14);
//        System.out.println(r7);
//rs.ajouter(r7);

   
 

    // Modifier l'événement par rang
//    List<Evenements> evts = es.afficher();
//    Evenements evt = evts.get(1);
//    System.out.println("Evénement avant modification : " + evt);
//    evt.setNbr_de_places(15);
//    evt.setLieu_evenement("Sousse");
//    es.modifier(evt);
//    System.out.println("Evénement après modification : " + evt);

    
}
   

      
      
     
      
      
    }
    

    
   
 

