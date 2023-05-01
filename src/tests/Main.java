/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import entities.JournalMood;
import entities.Mood;
import services.Journal;
import tools.MyConnection;
import services.Mood1;


/**
 *
 * @author LENOVO
 */
public class main {
    public static void main(String[] args) {
        //MaConnexion mc = new MaConnexion;
       // Mood1 m1 = new Mood1();
        Journal j1 = new Journal();
        
        
        //Mood m = new Mood (12 ,"happy", "happy" , 5 );
         //m1.ajouterMood(m);
         //System.out.println(m1.afficherMood());
         //m1.supprimerMood(36);
         //m1.modifierMood(m);
        JournalMood j = new JournalMood (22,40,17 );
        //j1.ajouterJournal(j);
        //System.out.println(j1.afficherJournal());
        //j1.supprimerJournal(23);
        j1.modifierJournal(j);
         
        
    }
}
