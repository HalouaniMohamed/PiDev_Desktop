/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.eshkili.test;

import tn.eshkili.entities.JournalMood;
import tn.eshkili.entities.Mood;
import tn.eshkili.services.Journal;
import tn.eshkili.utils.MaConnexion;
import tn.eshkili.services.Mood1;


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
        j1.supprimerJournal(23);
        j1.modifierJournal(j);
         
        
    }
}
