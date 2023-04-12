/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.eshkili.services;

import java.util.List;
import tn.eshkili.entities.JournalMood;

/**
 *
 * @author LENOVO
 */
public interface InterfaceJournal {
           public void ajouterJournal(JournalMood J);
   public List<JournalMood> afficherJournal();
    public void supprimerJournal(int id);
    public void modifierJournal(JournalMood J);
}
