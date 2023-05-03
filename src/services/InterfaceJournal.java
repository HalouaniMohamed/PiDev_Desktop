/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import entities.JournalMood;

/**
 *
 * @author LENOVO
 * @param <T>
 */
public interface InterfaceJournal<T> {
   public void ajouterJournal(JournalMood J);
   public List<JournalMood> afficherJournal();
    public void supprimerJournal(int id);
    public void modifierJournal(JournalMood J);
}
